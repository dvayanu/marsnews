package net.anotheria.marsnews.ranks.business;

import java.util.List;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;

public interface RanksService {
	public RankedCountry getRankedCountry(int countryId);
	
	public void updateRanks(List<RankedCountry> toUpdate);
	
	public List<RankedCountry> getRankedCountries();
	
	public List<RankedCountry> getCountriesInClan(String clan);
	
	public List<String> getClanNames();
	
	public List<RankedCountry> getCountriesByName(String name);
		
	public RankingVO getTotalRanking();
	
	public List<RankingVO> getClanRankings();
	
	public RankingVO getClanRanking(String clanName);
	
	public List<RankedCountry> getCountryHistory(int countryNumber);
	
}
