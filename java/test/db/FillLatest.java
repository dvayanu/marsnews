package test.db;

import java.io.FileOutputStream;
import java.util.List;

import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.NewsRequest;
import net.anotheria.marsnews.marsconnector.parser.XMLParser;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.persistence.NewsPersistenceService;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceJDBCFactory;

import org.apache.log4j.BasicConfigurator;


public class FillLatest {
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		NewsPersistenceService service = new NewsPersistenceServiceJDBCFactory().getService();
		System.out.println("LastId: "+service.getLastNewsEntryId());
	
		int lastId = service.getLastNewsEntryId();
		NewsRequest request = new NewsRequest();
		request.setLastId(lastId);
		request.setCount(200);
		
		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		HttpClientProperties properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);
		
		HttpGetter g = new HttpGetter(properties);
		HttpResult result = g.post(request);
		System.out.println("ResultCode: "+result.getResultCode());
		System.out.println("Headers: "+result.getHeaders());
		System.out.println("Server replied: "+result.getDataAsString().length()+" bytes.");
		
		FileOutputStream fOut = new FileOutputStream("test-from-"+lastId+".xml");
		fOut.write(result.getData());
		fOut.flush();
		fOut.close();

		//parsing
		List<NewsEntry> entries = new XMLParser().parseNewsResponse(result.getDataAsString());
		System.out.println("Parsed "+entries.size()+" news entries");
		for (int i=0; i<entries.size(); i++){
			System.out.println(entries.get(i));
		}
		service.importNewsEntries(entries);
	}
}
