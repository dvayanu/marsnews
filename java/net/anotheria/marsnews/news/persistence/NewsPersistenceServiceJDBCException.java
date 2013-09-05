package net.anotheria.marsnews.news.persistence;

import java.sql.SQLException;

import net.anotheria.db.dao.DAOException;


public class NewsPersistenceServiceJDBCException extends NewsPersistenceServiceException{
	public NewsPersistenceServiceJDBCException(String message){
		super(message);
	}
	public NewsPersistenceServiceJDBCException(SQLException e){
		super("Undelying DB Error: "+e.getMessage());
	}
	public NewsPersistenceServiceJDBCException(DAOException e){
		super("Undelying DAO Error: "+e.getMessage());
	}

}
