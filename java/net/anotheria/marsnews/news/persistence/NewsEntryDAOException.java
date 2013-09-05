package net.anotheria.marsnews.news.persistence;

import net.anotheria.db.dao.DAOException;


public class NewsEntryDAOException extends DAOException{
	public NewsEntryDAOException(){
		
	}
	
	public NewsEntryDAOException(String aMessage){
		super(aMessage);
	}
}
