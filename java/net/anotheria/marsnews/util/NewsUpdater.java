package net.anotheria.marsnews.util;

import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.NewsRequest;
import net.anotheria.marsnews.marsconnector.parser.XMLParser;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsService;
import net.anotheria.marsnews.news.business.NewsServiceFactory;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.util.List;


public class NewsUpdater extends Thread{
	
	private static Logger log = Logger.getLogger(NewsUpdater.class);
	
	public static final long UPDATE_INTERVAL_FULL = 1000*60;
	public static final long UPDATE_INTERVAL_SHORT = 1000*30;
	
	private volatile boolean running; 
	
	public NewsUpdater(){
		running = true;
	}
	
	private synchronized boolean isRunning(){
		return running;
	}
	
	public synchronized void stopRunning(){
		running = false;
	}
	
	public void run(){
		
		try{
			sleep(3000);
		}catch(InterruptedException ignored){}
		if (log!=null)
			log.info("Updater starting NOW!");
		
		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		HttpClientProperties properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);
		
		XMLParser xmlParser = new XMLParser();
		NewsService service = NewsServiceFactory.getInstance();
		long sleepTime = UPDATE_INTERVAL_FULL;
		while(isRunning()){
			sleepTime = UPDATE_INTERVAL_FULL;
			try{
				int lastId = 0;
			
				try{
					lastId = service.getNewestNewsEntry().getId();
				}catch(Exception ignored){
					log.warn("geting last id failed, empty db?");
				}
				log.info("Starting update, last id was "+lastId);
				NewsRequest request = new NewsRequest();
				request.setLastId(lastId);
				request.setCount(200);
				
				HttpGetter g = new HttpGetter(properties);
				HttpResult result = g.post(request);
				log.debug("Result code: "+result.getResultCode());
				log.debug("Server returned "+result.getDataAsString().length()+" bytes.");
	
				FileOutputStream fOut = new FileOutputStream("dump-from-"+lastId+".xml");
				fOut.write(result.getData());
				fOut.flush();
				fOut.close();
	
				//parsing
				List<NewsEntry> entries = xmlParser.parseNewsResponse(result.getDataAsString());
				fOut = new FileOutputStream("ids-from-"+lastId+".xml");
				for (NewsEntry entry : entries){
					fOut.write((entry.getId()+"\n").getBytes());
				}
				fOut.flush();
				fOut.close();
				log.info("Parsed "+entries.size()+" entries");
				
				service.importNewsEntries(entries);
				
				sleepTime = entries.size()<200 ? UPDATE_INTERVAL_SHORT : UPDATE_INTERVAL_FULL; 
			}catch(Exception e){
				log.error("Updater caught an error: ", e);
			}
			try{
				log.debug("Sleeping "+sleepTime);
				sleep(sleepTime);
				
			}catch(InterruptedException ignored){}
		}
		if (log!=null)
			log.info("News Updater STOPED!");
	}
}
