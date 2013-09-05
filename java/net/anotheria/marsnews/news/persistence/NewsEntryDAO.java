package net.anotheria.marsnews.news.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.anotheria.db.dao.DAO;
import net.anotheria.db.dao.DAOException;
import net.anotheria.db.dao.DAOSQLException;
import net.anotheria.db.dao.RowMapper;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackTypeHelper;

import org.apache.log4j.Logger;


public class NewsEntryDAO implements DAO{
	
	public static final String TABNAME = "news";
	
	public static final String ATT_NAME_INSTALLATION_ID = "iid";
	public static final String ATT_NAME_SECRET          = "secret";
	public static final String ATT_NAME_PB_NUMBER       = "pb_card";
	public static final String ATT_NAME_PB_PIN          = "pb_pin";
	
	public static final int ATT_POS_INSTALLATION_ID = 1;
	public static final int ATT_POS_SECRET          = 2;
	public static final int ATT_POS_PB_NUMBER       = 3;
	public static final int ATT_POS_PB_PIN          = 4;
	
	public static final String ATT_NAME_ENTRY_ID = "news_id";
	public static final String ATT_NAME_ATTACKER_ID = "attacker_id";
	public static final String ATT_NAME_ATTACKER_NAME = "attacker_name";
	public static final String ATT_NAME_ATTACKER_CLAN = "attacker_clan";
	public static final String ATT_NAME_DEFENDER_ID = "defender_id";
	public static final String ATT_NAME_DEFENDER_NAME = "defender_name";
	public static final String ATT_NAME_DEFENDER_CLAN = "defender_clan";
	public static final String ATT_NAME_KILL = "kill";
	public static final String ATT_NAME_RESULT1 = "result1";
	public static final String ATT_NAME_RESULT2 = "result2";
	public static final String ATT_NAME_TIMESTAMP  = "timestamp";
	public static final String ATT_NAME_ATTACK_TYPE = "type";
	

	public static final String ALL_ATTRIBUTES[] = {
		ATT_NAME_ENTRY_ID,
		ATT_NAME_ATTACKER_ID,
		ATT_NAME_ATTACKER_NAME,
		ATT_NAME_ATTACKER_CLAN,
		ATT_NAME_DEFENDER_ID,
		ATT_NAME_DEFENDER_NAME,
		ATT_NAME_DEFENDER_CLAN,
		ATT_NAME_KILL,
		ATT_NAME_RESULT1,
		ATT_NAME_RESULT2,
		ATT_NAME_TIMESTAMP,
		ATT_NAME_ATTACK_TYPE,
	};
	
	public static final int getAttributePosition(String attributeName)throws DAOException{
		for (int i=0; i<ALL_ATTRIBUTES.length; i++)
			if (ALL_ATTRIBUTES[i].equals(attributeName))
				return i+1;
		throw new DAOException("Unknown attribute: "+attributeName);
	}
	
	public static final String getAttributeList(boolean useQuestionMark){
		String ret = "";
		for (int i=0; i<ALL_ATTRIBUTES.length; i++){
			if (ret.length()>0)
				ret += ", ";
			ret += useQuestionMark ? "?" : ALL_ATTRIBUTES[i];
		}
		return ret;
	}
	
	public static final String SQL_CREATE  = "INSERT INTO "+TABNAME+" ("+getAttributeList(false)+") VALUES ("+getAttributeList(true)+")";
	public static final String SQL_UPDATE  = "UPDATE "+TABNAME+" SET "+ATT_NAME_SECRET+" = ?, "+ATT_NAME_PB_NUMBER+" = ?, "+ATT_NAME_PB_PIN+" = ? WHERE "+TABNAME+"."+ATT_NAME_INSTALLATION_ID+"=?";
	public static final String SQL_DELETE  = "DELETE FROM "+TABNAME+" WHERE "+TABNAME+"."+ATT_NAME_INSTALLATION_ID+" = ?";
	public static final String SQL_READ    = "SELECT * FROM "+TABNAME+" WHERE "+TABNAME+"."+ATT_NAME_INSTALLATION_ID+ " = ?";
	public static final String SQL_READALL = "SELECT "+getAttributeList(false)+" FROM "+TABNAME;
	
	private static Logger log = Logger.getLogger(NewsEntryDAO.class);
	
	private RowMapper<NewsEntry> rowMapper = new NewsEntryRowMapper();
	
	private int lastNewsId;
	
	protected void finish(Statement st) {
		/*
		try{
			if (st!=null && !st.isClosed())
				st.close();
		}catch(SQLException ignored){
			log.warn("finish("+st+")", ignored);
		}
		//*/
/*		try{
			if (!con.isClosed())
				con.close();
		}catch(SQLException ignored){}
*/		
	}
	
	public int getLastNewsId(){
		return lastNewsId;
	}
	
	public void init(Connection con) throws DAOException{
		log.debug("init() called");
		Statement st = null;
		try{
			con.setAutoCommit(true);
			st = con.createStatement();
			st.execute(("SELECT MAX("+ATT_NAME_ENTRY_ID+") FROM news;"));
			ResultSet set = st.getResultSet();
			int maxId = 0;
			if (set.next())
				maxId = set.getInt(1);
				
			lastNewsId = maxId;
			log.info("lastId is "+lastNewsId);
			set.close();
			st.close();
		}catch(SQLException e){
			log.error("init", e);
			throw new DAOSQLException(e);
		}finally{
			finish(st);
		}

	}
	
	public List<NewsEntry> getAllEntries(Connection con) throws DAOException{
		Statement s = null;
		List<NewsEntry> ret = new ArrayList<NewsEntry>();
		try{
			con.setAutoCommit(true);
			s = con.createStatement();
			ResultSet result = s.executeQuery(SQL_READALL);
			
			while(result.next())
				ret.add(rowMapper.map(result));
			return  ret;
		}catch(SQLException e){
			//log.error("createInstallation("+con+", "+secret+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(s);
		}
	}
	
	public void importEntries(Connection con, List<NewsEntry> entries) throws DAOException{
		PreparedStatement ps = null;
		try{
			con.setAutoCommit(false);

			log.debug("importing "+entries.size()+" entries");
			
			int newLastId = getLastNewsId();
			for (int i=0; i<entries.size(); i++){
				NewsEntry entry = entries.get(i);
				if (entry.getId()>newLastId)
					newLastId = entry.getId();

				log.debug("Importing "+entry);

				
				ps = con.prepareStatement(SQL_CREATE);
			

				ps.setInt(1, entry.getId());
				ps.setInt(2, entry.getAttackerId());
				ps.setString(3, entry.getAttackerName());
				ps.setString(4, entry.getAttackerClan());
				ps.setInt(5, entry.getDefenderId());
				ps.setString(6, entry.getDefenderName());
				ps.setString(7, entry.getDefenderClan());

				ps.setBoolean(8, entry.isKill());
				ps.setInt(9, entry.getResult1());
				ps.setInt(10, entry.getResult2());
				ps.setLong(11, entry.getTimestamp());
				ps.setInt(12, AttackTypeHelper.type2int(entry.getType()));

				int rows = ps.executeUpdate();

				//log.debug("updated rows: "+rows);
				if (rows!=1)
					throw new DAOException("Number of updated rows should be 1, not: "+rows);
			}
			con.commit();
			lastNewsId = newLastId;
			System.out.println("commited, lastNewsId is now: "+lastNewsId);
	
			
		}catch(SQLException e){
			e.printStackTrace();
			//log.error("createInstallation("+con+", "+secret+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(ps);
		}
	}

/*	public void updateInstallation(Connection con, Installation installation) throws DAOException{
		PreparedStatement ps = null;
		try{
			con.setAutoCommit(true);
			
			log.debug(SQL_UPDATE);
			ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, installation.getSecret());
			ps.setString(2, installation.getCardNumber());
			ps.setString(3, installation.getPin());
			ps.setInt(4, Integer.parseInt(installation.getInstallationId()));
			
			int rows = ps.executeUpdate();
	
			log.debug("updated rows: "+rows);
			if (rows!=1)
				throw new DAOException("Number of updated rows should be 1, not: "+rows);
			
		}catch(SQLException e){
			log.error("updateInstallation("+con+", "+installation+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(ps);
		}
		
	}

	public void removeInstallation(Connection con, String installationId) throws DAOException{
		PreparedStatement ps = null;
		try{
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_DELETE);
			int id = Integer.parseInt(installationId);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows!=1 && rows!=0){
				log.warn("Deleted more than one row of installationId: "+installationId);
			}
			
		}catch(SQLException e){
			log.error("removeInstallation("+con+", "+installationId+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(ps);
		}
	}
	
	public Installation getInstallation(Connection con, String installationId) throws DAOException{
		PreparedStatement ps = null;
		try{
			con.setAutoCommit(true);
			ps = con.prepareStatement(SQL_READ);
			int id = Integer.parseInt(installationId);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			if (!result.next())
				throw new InstallationDAONoEntityForIdException(id);
			return rowMapper.map(result);
			
		}catch(SQLException e){
			log.error("getInstallation("+con+", "+installationId+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(ps);
		}
	}

	public List<Installation> getInstallations(Connection con) throws DAOException{
		List<Installation> ret = new ArrayList<Installation>();
		Statement s = null;
		try{
			con.setAutoCommit(true);
			s = con.createStatement();
			ResultSet result = s.executeQuery(SQL_READALL);
			
			while(result.next())
				ret.add(rowMapper.map(result));
			return  ret;
			
		}catch(SQLException e){
			log.error("getInstallations("+con+")", e);
			throw new DAOSQLException(e);
		}finally{
			finish(s);
		}
	}
*/
	public void createStructure(Connection connection) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public void deleteStructure(Connection connectiion) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	

}
