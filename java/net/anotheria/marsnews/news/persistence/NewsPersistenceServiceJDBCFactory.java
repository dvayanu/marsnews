package net.anotheria.marsnews.news.persistence;


public class NewsPersistenceServiceJDBCFactory {
	private static NewsPersistenceServiceJDBCImpl instance = new NewsPersistenceServiceJDBCImpl();
	
	public NewsPersistenceService getService(){
		return instance;
	}
}
