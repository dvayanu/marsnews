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


public class ManualFill {
	public static void main(String a[]) throws Exception{
		BasicConfigurator.configure();
		NewsPersistenceService service = new NewsPersistenceServiceJDBCFactory().getService();
		System.out.println("LastId: "+service.getLastNewsEntryId());
	
		
		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		HttpClientProperties properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);

		
		boolean continuework = true;
		while(continuework){
			int lastId = service.getLastNewsEntryId();
			NewsRequest request = new NewsRequest();
			request.setLastId(lastId);
			request.setCount(200);
			
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
			service.importNewsEntries(entries);
			
			if (entries.size()<200){
				System.out.println("Ok, got less than 200 ("+entries.size()+") in last request, exiting.");
				continuework = false;
			}else{
				try{
					System.out.println("............ sleeping ............");
					Thread.currentThread().sleep(30000);
					
				}catch(InterruptedException ignored){}
			}
			
		}
		
		

	}
}
