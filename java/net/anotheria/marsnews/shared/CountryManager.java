package net.anotheria.marsnews.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.anotheria.marsnews.news.business.NewsEntry;

public class CountryManager {
	private static CountryManager instance;
	
	private Map<Integer, Country> countries;
	private Map<String, List<Integer>> countriesInClans;
	
	
	public static CountryManager getInstance(){
		if (instance==null)
			instance = new CountryManager();
		return instance;
	}
	
	private CountryManager(){
		countries = new HashMap<Integer, Country>();
		countriesInClans = new HashMap<String, List<Integer>>();
	}
	
	public void process(NewsEntry e){
		if (countries.get(e.getAttackerId())==null)
			countries.put(e.getAttackerId(), new Country(e.getAttackerId(), e.getAttackerName(), e.getAttackerClan()));
		if (countries.get(e.getDefenderId())==null)
			countries.put(e.getDefenderId(), new Country(e.getDefenderId(), e.getDefenderName(), e.getDefenderClan()));
		notifyCountryInClan(e.getAttackerClanForStats(), e.getAttackerId());
		notifyCountryInClan(e.getDefenderClanForStats(), e.getDefenderId());
		if (e.isKill())
			countries.get(e.getDefenderId()).setAlive(false);
	}
	
	public Country getCountry(int id){
		return countries.get(id);
	}
	
	public int getAmountOfCountries(){
		return countries.size();
	}
	
	private void notifyCountryInClan(String clan, int countryId){
		List<Integer> countries = countriesInClans.get(clan);
		if (countries==null){
			countries = new ArrayList<Integer>();
			countriesInClans.put(clan, countries);
		}
		if (countries.indexOf(countryId)==-1)
			countries.add(countryId);
	}
	
	public List<String> getClans(){
		List<String> ret = new ArrayList<String>();
		
		ret.addAll(countriesInClans.keySet());
		
		return ret;
	}
	
	public int getCountryCountInClan(String clan){
		try{
			return countriesInClans.get(clan).size();
		}catch(Exception e){}
		return 0;
	}
	
	public int getAliveCountryCountInClan(String clan){
		int ret = 0;
		List<Integer> countryIds = countriesInClans.get(clan);
		for(int i=0;i<countryIds.size(); i++){
			if (getCountry(countryIds.get(i)).isAlive())
				ret++;
		}
		return ret;
	}
}
