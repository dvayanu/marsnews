package net.anotheria.marsnews.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.RankRequest;
import net.anotheria.marsnews.marsconnector.parser.XMLParser;
import net.anotheria.marsnews.ranks.business.RankServiceFactory;
import net.anotheria.marsnews.ranks.business.RanksService;
import net.anotheria.util.Date;

import org.apache.log4j.Logger;

public class RanksUpdater 
	 extends Thread{
			
	private static Logger log = Logger.getLogger(NewsUpdater.class);
	
	public static final long UPDATE_INTERVAL = 1000*60*10;
	
	private volatile boolean running; 
	private XMLParser xmlParser = new XMLParser();
	private RanksService ranksService = RankServiceFactory.createRanksService();
	
	public RanksUpdater(){
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
		log.info("RanksUpdater starting NOW!");
		boolean firstUpdateDone = false;
		
		long sleepTime = UPDATE_INTERVAL;
		while(isRunning()){
			long now = System.currentTimeMillis();
			if (doUpdate(now) || !firstUpdateDone){
				try{
					log.info("Starting update... "+new Date(now));

					runUpdate();
					firstUpdateDone = true;
				}catch(Exception e){
					log.error("Updater caught an error: ", e);
				}
			}
			try{
				log.debug("Sleeping "+sleepTime);
				sleep(sleepTime);
				
			}catch(InterruptedException ignored){}
		}
		log.info("RanksUpdater STOPED!");
	}
	
	public void runUpdate() throws IOException{
		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		HttpClientProperties properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);

		RankRequest request = new RankRequest();
		
		HttpGetter g = new HttpGetter(properties);
		HttpResult result = g.post(request);
		log.debug("Result code: "+result.getResultCode());
		log.debug("Server returned "+result.getDataAsString().length()+" bytes.");

		FileOutputStream fOut = new FileOutputStream("scores-"+System.currentTimeMillis()+".xml");
		fOut.write(result.getData());
		fOut.flush();
		fOut.close();

		//parsing
		List<RankedCountry> entries = xmlParser.parseRanksResponse(result.getDataAsString());
		log.info("Parsed "+entries.size()+" countries.");
		ranksService.updateRanks(entries);
	}
	
	private boolean doUpdate(long time){
		Date d = new Date(time);
		if (d.getMin()>0 && d.getMin()<10)
			return true;
		return false;
	}
	
	public static void main(String a[]) throws Exception{
		new RanksUpdater().runUpdate();
	}

}
