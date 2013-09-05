package test.service;

import java.util.List;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsService;
import net.anotheria.marsnews.news.business.NewsServiceFactory;
import net.anotheria.marsnews.news.business.NewsServiceImpl;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.news.business.NewsTotalsUtility;
import net.anotheria.util.Date;

import org.apache.log4j.BasicConfigurator;

public class BasicTestNewsService {
	
	public static final long HOUR = 1000L*60*60;
	public static final long _12H = 12*HOUR;
	public static final long DAY = 24*HOUR;
	public static final long _72H = 72*HOUR;
	
	public static final String INT_DESC[] = {
		"All", "1H", "72H", "12H", "24H", "1W",
	};
	
	public static final long INT[]={
		System.currentTimeMillis(),
		HOUR,
		_72H,
		_12H,
		DAY,
		DAY*7,
	};
	
	private static NewsService service;
	
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		service = NewsServiceFactory.getInstance();
		System.out.println("LastUpdate: "+service.getLastUpdate()+", "+new Date(service.getLastUpdate()));
		
		NewsEntry latest = service.getNewestNewsEntry();
		System.out.println("latest news entry is : "+latest);
		
		testForCountry(16);
		testForClan("FTW");
		testForClan("WDU");
		testForClan("GoD");
		testGame();
	}
	
	private static void testForCountry(int countryId) throws Exception{
		System.out.println("============= "+countryId+" ===============");
		List<NewsEntry> news = null;
		
		for (int i=0; i<INT.length; i++){
			news = service.getNewsForCountry(countryId, System.currentTimeMillis() - INT[i]);
			System.out.println("Got "+news.size()+" news for "+INT_DESC[i]);
			NewsTotals totals = NewsTotalsUtility.calculateTotalsForCountry(countryId ,news);
			System.out.println("Totals: "+totals);
		}
		/*
		for (int i=0; i<news.size(); i++){
			System.out.println(news.get(i));
		}
		*/
		
	}

	private static void testForClan(String clan) throws Exception{
		System.out.println("============= "+clan+" ===============");
		List<NewsEntry> news = null;
		
		for (int i=0; i<INT.length; i++){
			news = service.getNewsForClan(clan, System.currentTimeMillis() - INT[i]);
			System.out.println("Got "+news.size()+" news for "+INT_DESC[i]);
			NewsTotals totals = NewsTotalsUtility.calculateTotalsForClan(clan ,news);
			System.out.println("Totals: "+totals);
		}
		/*
		for (int i=0; i<news.size(); i++){
			System.out.println(news.get(i));
		}
		*/
		
	}
	
	private static void testGame() throws Exception{
		System.out.println("============= GAME ===============");
		List<NewsEntry> news = null;
		
		for (int i=0; i<INT.length; i++){
			news = service.getNews(System.currentTimeMillis() - INT[i]);
			System.out.println("Got "+news.size()+" news for "+INT_DESC[i]);
			NewsTotals totals = NewsTotalsUtility.calculateTotals(news);
			System.out.println("Totals: "+totals);
		}
		/*
		for (int i=0; i<news.size(); i++){
			System.out.println(news.get(i));
		}
		*/
		
	}

}
