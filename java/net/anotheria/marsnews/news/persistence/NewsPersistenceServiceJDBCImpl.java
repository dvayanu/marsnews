package net.anotheria.marsnews.news.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.anotheria.db.dao.DAOException;
import net.anotheria.db.service.BasePersistenceServiceJDBCImpl;
import net.anotheria.marsnews.news.business.NewsEntry;


public class NewsPersistenceServiceJDBCImpl extends BasePersistenceServiceJDBCImpl implements NewsPersistenceService{

	

	private NewsEntryDAO dao;
	
	NewsPersistenceServiceJDBCImpl(){
	}
	
	public void init(){
		super.init();
		dao = new NewsEntryDAO();
		try{
			dao.init(getConnection());
		}catch(DAOException e){
			log.fatal("init failed (dao) ",e );
			throw new RuntimeException(e.getMessage());
		}catch(SQLException e){
			log.fatal("init failed (sql) ",e );
			throw new RuntimeException(e.getMessage());
		}
	}

	public int getLastNewsEntryId() throws NewsPersistenceServiceException {
		return dao.getLastNewsId();
	}

	public List<NewsEntry> getAllNewsEntries() throws NewsPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			return dao.getAllEntries(c);
		}catch(SQLException e){
			log.error("getAllNewsEntries()", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}

	public void importNewsEntries(List<NewsEntry> entries) throws NewsPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			dao.importEntries(c, entries);
		}catch(SQLException e){
			log.error("importEntries("+entries.size()+" parameters)", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}

	public void importNewsEntry(NewsEntry entry) throws NewsPersistenceServiceException {
		ArrayList<NewsEntry> list = new ArrayList<NewsEntry>(1);
		list.add(entry);
		importNewsEntries(list);
			
		
	}
	/*
	 
	 public Installation createInstallation(String secret) throws InstallationPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			Installation ret = dao.createInstallation(c, secret);
			return ret;
		}catch(SQLException e){
			log.error("createInstallation("+secret+")", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}

	public Installation getInstallation(String installationId) throws InstallationPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			return dao.getInstallation(c, installationId);
		}catch(InstallationDAONoEntityForIdException e){
			throw new NoPersistedInstallationFoundException(installationId);
		}catch(SQLException e){
			log.error("getInstallation("+installationId+")", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}

	public List<Installation> getInstallations() throws InstallationPersistenceServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeInstallation(String installationId) throws InstallationPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			dao.removeInstallation(c, installationId);
		}catch(SQLException e){
			log.error("removeInstallation("+installationId+")", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}

	public void updateInstallation(Installation installation) throws InstallationPersistenceServiceException {
		Connection c = null;
		try{
			c = getConnection();
			dao.updateInstallation(c, installation);
		}catch(SQLException e){
			log.error("updateInstallation("+installation+")", e);
			throw new NewsPersistenceServiceJDBCException(e);
		}catch(DAOException e){
			throw new NewsPersistenceServiceJDBCException(e);
		}finally{
			release(c);
		}
	}
*/
}
