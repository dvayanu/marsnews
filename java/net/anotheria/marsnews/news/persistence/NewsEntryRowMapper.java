package net.anotheria.marsnews.news.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.anotheria.db.dao.RowMapper;
import net.anotheria.db.dao.RowMapperException;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackTypeHelper;

import org.apache.log4j.Logger;



public class NewsEntryRowMapper extends RowMapper<NewsEntry>{
	
	private static Logger log = Logger.getLogger(NewsEntryRowMapper.class);

	private static int ATT_POS_ENTRY_ID ;
	private static int ATT_POS_ATTACKER_ID ;
	private static int ATT_POS_ATTACKER_NAME ;
	private static int ATT_POS_ATTACKER_CLAN ;
	private static int ATT_POS_DEFENDER_ID ;
	private static int ATT_POS_DEFENDER_NAME ;
	private static int ATT_POS_DEFENDER_CLAN ;
	private static int ATT_POS_KILL ;
	private static int ATT_POS_RESULT1 ;
	private static int ATT_POS_RESULT2 ;
	private static int ATT_POS_TIMESTAMP ;
	private static int ATT_POS_ATTACK_TYPE;

	static{
		try{
			ATT_POS_ENTRY_ID = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_ENTRY_ID);
			ATT_POS_ATTACKER_ID = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_ATTACKER_ID);
			ATT_POS_ATTACKER_NAME = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_ATTACKER_NAME);
			ATT_POS_ATTACKER_CLAN = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_ATTACKER_CLAN);
			ATT_POS_DEFENDER_ID = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_DEFENDER_ID);
			ATT_POS_DEFENDER_NAME = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_DEFENDER_NAME);
			ATT_POS_DEFENDER_CLAN = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_DEFENDER_CLAN);
			ATT_POS_KILL = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_KILL);
			ATT_POS_RESULT1 = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_RESULT1);
			ATT_POS_RESULT2 = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_RESULT2);
			ATT_POS_TIMESTAMP = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_TIMESTAMP);
			ATT_POS_ATTACK_TYPE = NewsEntryDAO.getAttributePosition(NewsEntryDAO.ATT_NAME_ATTACK_TYPE);
		}catch(Exception e){
			log.fatal("DB Schema is not ok: ", e);
		}
	};
	
	public NewsEntryRowMapper(){
		
	}
	
	@Override
	public NewsEntry map(ResultSet row) throws RowMapperException{
/*		
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
*/
		

		try{
			NewsEntry entry = new NewsEntry();
			entry.setId(row.getInt(ATT_POS_ENTRY_ID));
			
			entry.setAttackerId(row.getInt(ATT_POS_ATTACKER_ID));
			entry.setAttackerName(row.getString(ATT_POS_ATTACKER_NAME));
			entry.setAttackerClan(row.getString(ATT_POS_ATTACKER_CLAN));
			
			entry.setDefenderId(row.getInt(ATT_POS_DEFENDER_ID));
			entry.setDefenderName(row.getString(ATT_POS_DEFENDER_NAME));
			entry.setDefenderClan(row.getString(ATT_POS_DEFENDER_CLAN));
			
			entry.setKill(row.getBoolean(ATT_POS_KILL));
			entry.setResult1(row.getInt(ATT_POS_RESULT1));
			entry.setResult2(row.getInt(ATT_POS_RESULT2));
			
			entry.setTimestamp(row.getLong(ATT_POS_TIMESTAMP));
			entry.setType(AttackTypeHelper.int2type(row.getInt(ATT_POS_ATTACK_TYPE)));
			
			return entry;
		}catch(SQLException e){
			log.error("map", e);
			throw new RowMapperException(e);
		}
	}
	
}
