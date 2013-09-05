package net.anotheria.marsnews.ranks.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.anotheria.anodoc.query2.QueryProperty;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.gen.rankedcountries.service.persistence.IRankedCountriesPersistenceService;
import net.anotheria.marsnews.gen.rankedcountries.service.persistence.RankedCountriesPersistenceServiceException;
import net.anotheria.marsnews.gen.rankedcountries.service.persistence.RankedCountriesPersistenceServiceFactory;
import net.anotheria.marsnews.gen.rankedcountries.service.persistence.RankedCountryDAO;

import org.apache.log4j.Logger;

public class RanksServiceImpl implements RanksService{
	
	private IRankedCountriesPersistenceService pService;
	private ArrayList<RankedCountry> cachedList;
	private HashMap<Integer, RankedCountry> cachedMap;
	private List<String> cachedClanNames;
	
	private RankingVO totalRanking;
	private Map<String, RankingVO> clanRankings;
	
	public List<String> getClanNames() {
		return cachedClanNames;
	}

	private static Logger log = Logger.getLogger(RanksServiceImpl.class);
	
	public RanksServiceImpl(){
		pService = RankedCountriesPersistenceServiceFactory.createRankedCountriesPersistenceService();
		cachedList = new ArrayList<RankedCountry>();
	}

	public RankedCountry getRankedCountry(int countryId) {
		return cachedMap==null ? null : cachedMap.get(countryId);
	}

	public void updateRanks(List<RankedCountry> toUpdate) {
		log.debug("Updating "+toUpdate.size()+" entries");
		cachedList = new ArrayList<RankedCountry>(toUpdate.size());
		cachedList.addAll(toUpdate);
		cachedClanNames = new ArrayList<String>();
		
		Set<String> clanNames = new HashSet<String>();
	
		cachedMap = new HashMap<Integer, RankedCountry>();
		for (RankedCountry c : cachedList){
			cachedMap.put(c.getCountryId(), c);
			if (c.getClan().length()>0)
				clanNames.add(c.getClan());
			
		}
		for (int i=0; i<toUpdate.size(); i++){
			try{
				pService.createRankedCountry(toUpdate.get(i));
			}catch(RankedCountriesPersistenceServiceException e){
				log.error("updateRanks",e);
			}
		}
		
		cachedClanNames.addAll(clanNames);
		Collections.sort(cachedClanNames);
		
		updateRankings(cachedList);
	}
	
	private void updateRankings(List<RankedCountry> countries){
		HashMap<String, RankingVO> newClanRanks = new HashMap<String, RankingVO>();
		RankingVO newTotalRanking = new RankingVO();
		
		for (RankedCountry c : countries){
			newTotalRanking.proceedCountry(c);
			String clan = c.getClan();
			if (clan!=null && clan.length()>0){
				RankingVO forClan = newClanRanks.get(clan);
				if (forClan==null){
					forClan = new RankingVO(clan);
					newClanRanks.put(clan, forClan);
				}
				forClan.proceedCountry(c);
			}
		}
		
		totalRanking = newTotalRanking;
		clanRankings = newClanRanks;
	}

	public RankingVO getTotalRanking(){
		return totalRanking;
	}
	
	public List<RankingVO> getClanRankings(){
		ArrayList<RankingVO> ret = new ArrayList<RankingVO>();
		ret.addAll(clanRankings.values());
		return ret;
	}
	
	public RankingVO getClanRanking(String clanName){
		return clanRankings.get(clanName);
	}
	
	public List<RankedCountry> getCountriesInClan(String clan) {
		ArrayList<RankedCountry> ret = new ArrayList<RankedCountry>();
		List<RankedCountry> all = cachedList;
		for (RankedCountry c : all){
			if (c.getClan().equals(clan))
				ret.add(c);
		}
		
		return ret;
			
	}

	public List<RankedCountry> getRankedCountries() {
		return cachedList;
	}
	
	public List<RankedCountry> getCountriesByName(String name){
		List<RankedCountry> src = cachedList;
		List<RankedCountry> ret = new ArrayList<RankedCountry>();
		for (RankedCountry c : src)
			if (c.getCountryName().indexOf(name)!=-1)
				ret.add(c);
		return ret;
	}
	
	public List<RankedCountry> getCountryHistory(int countryNumber) {
		QueryProperty p = new QueryProperty(RankedCountryDAO.ATT_NAME_COUNTRYID, countryNumber);
		try{
			return pService.getRankedCountrysByProperty(p);
		}catch(RankedCountriesPersistenceServiceException e){
			log.error("getCountryHistory("+countryNumber+")", e);
		}
		return new ArrayList<RankedCountry>();
	}



}
