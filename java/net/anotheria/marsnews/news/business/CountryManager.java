package net.anotheria.marsnews.news.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CountryManager implements NewsServiceListener{
	private NewsService newsService;
	
	private static Logger log = Logger.getLogger(CountryManager.class);
	private static final CountryManager instance = new CountryManager();
	
	private Map<Integer, Country> countries;
	private Map<String, Clan> clans;
	
	
	private CountryManager(){
		countries = new HashMap<Integer, Country>();
		clans = new HashMap<String, Clan>();
		newsService = NewsServiceFactory.getInstance();
		newsService.addListener(this);
		recalculate();
		
	}
	
	public static final CountryManager getInstance(){
		return instance;
	}
	
	public void notifyNewsServiceUpdated() {
		recalculate();
	}
	
	public List<Country> getCountries(){
		ArrayList<Country> ret = new ArrayList<Country>();
		ret.addAll(countries.values());
		return ret;
	}
	
	public Country getCountry(int countryId){
		return countries.get(countryId);
	}
	
	public List<Country> getCountriesInClan(String clanId){
		ArrayList<Country> ret = new ArrayList<Country>();
		for (Iterator<Country> it = countries.values().iterator(); it.hasNext(); ){
			Country c = it.next();
			if (c.getCurrentClan().equals(clanId))
				ret.add(c);
		}
		return ret;
	}
	
	public List<Clan> getClans(){
		ArrayList<Clan> ret = new ArrayList<Clan>();
		ret.addAll(clans.values());
		return ret;
	}
	
/*	public List<String> getClansNames(){
		ArrayList<String> ret = new ArrayList<String>();
		try{
			for (Iterator<String> it = clans.keySet().iterator(); it.hasNext(); )
				ret.add(it.next());
		}catch(Exception e){
			log.error("getClansNames", e);
		}
		return ret;
	}
*/
	private void recalculate(){
		log.debug("recalculating");
		long startTime = System.currentTimeMillis();
		countries.clear();
		clans.clear();
		try{
			List<NewsEntry> news = newsService.getNews(0);
			for (int i=0; i<news.size(); i++){
				NewsEntry entry = news.get(i);
				check(entry.getTimestamp(), entry.getAttackerId(), entry.getAttackerName(), entry.getAttackerClan());
				check(entry.getTimestamp(), entry.getDefenderId(), entry.getDefenderName(), entry.getDefenderClan());
				countries.get(entry.getAttackerId()).processNewsEntry(entry);
				countries.get(entry.getDefenderId()).processNewsEntry(entry);
				
				checkClan(entry);
			}
		}catch(NewsServiceException e){
			log.error("recalculate", e);
		}
		
		log.debug("done recalculating countries in "+(System.currentTimeMillis()-startTime)+" ms.");
		
	}
	
	private void check(long timestamp, int id, String name, String clan){
		Country c = countries.get(id);
		if (c==null){
			c = new Country(id, name, clan, timestamp);
			countries.put(c.getId(), c);
		}else{
			c.process(timestamp, clan);
		}
	}
	
	private void checkClan(NewsEntry e){
		if (e.getAttackerClan().length()!=0){
			Clan c = clans.get(e.getAttackerClan());
			if (c==null){
				c = new Clan(e.getAttackerClan());
				clans.put(c.getName(), c);
			}
		}
			
		if (e.getDefenderClan().length()!=0){
			Clan c = clans.get(e.getDefenderClan());
			if (c==null){
				c = new Clan(e.getDefenderClan());
				clans.put(c.getName(), c);
			}
		}
	}


}
