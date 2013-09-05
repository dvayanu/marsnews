package net.anotheria.marsnews.news.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.anotheria.marsnews.news.persistence.NewsPersistenceService;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceException;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceJDBCFactory;

public class NewsServiceImpl implements NewsService {
	
	private ArrayList<NewsEntry> entries;
	private NewsPersistenceService pService;
	
	private long lastUpdateTimestamp;
	
	private List<NewsServiceListener> listeners;
	
	private static Logger log = Logger.getLogger(NewsServiceImpl.class);
	
	NewsServiceImpl(){
		listeners = new ArrayList<NewsServiceListener>();
		pService = new NewsPersistenceServiceJDBCFactory().getService();
		Logger.getLogger(NewsServiceImpl.class).debug("Initializing news from db.");
		updateFromDB();
	}

	public List<NewsEntry> getNews(long since) throws NewsServiceException {
		log.info("getNews("+since+") called.");
		List<NewsEntry> ret = new ArrayList<NewsEntry>();
		for (int i=0; i<entries.size(); i++){
			NewsEntry e = entries.get(i);
			if (e.getTimestamp()>since)
				ret.add(e);
		}
		log.info("returning "+ret.size()+" out of "+entries.size()+" entries.");
		return ret;
	
	}

	public List<NewsEntry> getNewsForCountry(int countryId, long since) throws NewsServiceException {
		List<NewsEntry> ret = new ArrayList<NewsEntry>();
		for (int i=0; i<entries.size(); i++){
			NewsEntry e = entries.get(i);
			if ((e.getAttackerId()==countryId || e.getDefenderId()==countryId) && e.getTimestamp()>since)
				ret.add(e);
		}
		return ret;
	}

	public List<NewsEntry> getFilteredNews(NewsFilter filter) throws NewsServiceException {
		List<NewsEntry> ret = new ArrayList<NewsEntry>();
		for (int i=0; i<entries.size(); i++){
			NewsEntry e = entries.get(i);
			if (filter.mayPass(e))
				ret.add(e);
		}
		return ret;
	}

	public List<NewsEntry> getNewsForClan(String clan, long since) throws NewsServiceException {
		List<NewsEntry> ret = new ArrayList<NewsEntry>();
		for (int i=0; i<entries.size(); i++){
			NewsEntry e = entries.get(i);
			if (e.getTimestamp()>since && (e.getAttackerClan().equals(clan) || e.getDefenderClan().equals(clan)))
				ret.add(e);
		}
		return ret;
	}

	public NewsEntry getNewestNewsEntry(){
		return entries.get(0); 
	}
	
	public long getLastUpdate(){
		return lastUpdateTimestamp;
	}
	
	public void importNewsEntries(List<NewsEntry> entries) throws NewsServiceException {
		try{
			log.info("Importing "+entries.size()+" entries.");
			pService.importNewsEntries(entries);
		}catch(NewsPersistenceServiceException e){
			log.error("importNewsEntries - importing one by one", e);
			for (NewsEntry entry : entries){
				try{
					pService.importNewsEntry(entry);
				}catch(NewsPersistenceServiceException e2){
					log.error("Repeating exception "+e2.getMessage()+" in "+entry+" giving up.", e2);
				}
			}
		}
		updateFromDB();
		
	}

	private void updateFromDB(){
		try{
			long startTime = System.currentTimeMillis();
			List<NewsEntry> newsFromDb = pService.getAllNewsEntries();
			ArrayList<NewsEntry> newList = new ArrayList<NewsEntry>(newsFromDb.size());
			for (int i=newsFromDb.size()-1; i>=0; i--)
				newList.add(newsFromDb.get(i));
			entries = newList;
			lastUpdateTimestamp = System.currentTimeMillis();
			
			log.info("Updated list with "+newList.size()+" entries from db "+(lastUpdateTimestamp-startTime)+" ms.");
			//System.out.println("notifying listeners: "+listeners);
			for (NewsServiceListener l : listeners)
				l.notifyNewsServiceUpdated();
		}catch(NewsPersistenceServiceException e){
			log.error("Couldn't get news from db!", e);
		}
	}

	public void addListener(NewsServiceListener listener) {
		listeners.add(listener);
	}

	public void removeListener(NewsServiceListener listener) {
		listeners.remove(listener);
	}
	
	

}
