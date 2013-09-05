package test.db;

import org.apache.log4j.BasicConfigurator;

import net.anotheria.marsnews.news.persistence.NewsPersistenceService;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceException;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceJDBCFactory;

public class Test1 {
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		NewsPersistenceService service = new NewsPersistenceServiceJDBCFactory().getService();
		System.out.println("LastId: "+service.getLastNewsEntryId());
	}
}
