package net.anotheria.marsnews.ranks.business;

import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_ACTIVE_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_AVERAGE_LAND;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_AVERAGE_NETWORTH;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_DEAD_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_GDI_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_LAND;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_NETWORTH;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_NETWORTH_LAND_RATIO;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_PROTECTION_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_TOP100_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_TOP10_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_TOP25_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_TOP50_COUNTRIES;
import static net.anotheria.marsnews.ranks.business.RankingVOSortType.SORT_BY_TOTAL_COUNTRIES;

import java.util.HashMap;
import java.util.Map;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.util.BasicComparable;
import net.anotheria.util.NumberUtils;
import net.anotheria.util.sorter.IComparable;

public class RankingVO implements IComparable{
	private String clan;
	
	private long networth;
	private int land;
	private int numberOfCountries;
	private Map<Integer, Integer> govTypes;
	private int deadCountries;
	private int countriesUnderProtection;
	private int gdiCountries;
	
	private int countriesInTop10;
	private int countriesInTop25;
	private int countriesInTop50;
	private int countriesInTop100;
	
	public RankingVO(){
		govTypes = new HashMap<Integer, Integer>();
	}
	
	public RankingVO(String aClan){
		this();
		clan = aClan;
	}
	
	public void proceedCountry(RankedCountry c){
		networth += c.getNetworth();
		land     += c.getLand();
		numberOfCountries++;
		if (c.getDead())
			deadCountries++;
		if (c.getGdi())
			gdiCountries++;
		if (c.getProtection())
			countriesUnderProtection++;
		
		Integer numberOfCountriesInGovType = govTypes.get(c.getGov());
		govTypes.put(c.getGov(), 
				numberOfCountriesInGovType == null ? 1 : numberOfCountriesInGovType+1);
		
		if (c.getRank()<=10)
			countriesInTop10++;
		if (c.getRank()<=25)
			countriesInTop25++;
		if (c.getRank()<=50)
			countriesInTop50++;
		if (c.getRank()<=100)
			countriesInTop100++;
	}

	public long getAverageNetworth(){
		return networth / numberOfCountries;
	}
	
	public long getNetworthLandRatio(){
		return networth / land;
	}

	public String getClan() {
		return clan;
	}


	public void setClan(String clan) {
		this.clan = clan;
	}


	public int getCountriesUnderProtection() {
		return countriesUnderProtection;
	}


	public void setCountriesUnderProtection(int countriesUnderProtection) {
		this.countriesUnderProtection = countriesUnderProtection;
	}


	public int getDeadCountries() {
		return deadCountries;
	}


	public void setDeadCountries(int deadCountries) {
		this.deadCountries = deadCountries;
	}


	public int getGdiCountries() {
		return gdiCountries;
	}


	public void setGdiCountries(int gdiCountries) {
		this.gdiCountries = gdiCountries;
	}


	public Map<Integer, Integer> getGovTypes() {
		return govTypes;
	}


	public void setGovTypes(Map<Integer, Integer> govTypes) {
		this.govTypes = govTypes;
	}


	public int getLand() {
		return land;
	}


	public void setLand(int land) {
		this.land = land;
	}


	public long getNetworth() {
		return networth;
	}


	public void setNetworth(long networth) {
		this.networth = networth;
	}


	public int getNumberOfCountries() {
		return numberOfCountries;
	}


	public void setNumberOfCountries(int numberOfCountries) {
		this.numberOfCountries = numberOfCountries;
	}

	public int getCountriesInTop10() {
		return countriesInTop10;
	}

	public void setCountriesInTop10(int countriesInTop10) {
		this.countriesInTop10 = countriesInTop10;
	}

	public int getCountriesInTop100() {
		return countriesInTop100;
	}

	public void setCountriesInTop100(int countriesInTop100) {
		this.countriesInTop100 = countriesInTop100;
	}

	public int getCountriesInTop25() {
		return countriesInTop25;
	}

	public void setCountriesInTop25(int countriesInTop25) {
		this.countriesInTop25 = countriesInTop25;
	}

	public int getCountriesInTop50() {
		return countriesInTop50;
	}

	public void setCountriesInTop50(int countriesInTop50) {
		this.countriesInTop50 = countriesInTop50;
	}
	
	public int getActiveCountries(){
		return numberOfCountries - deadCountries - countriesUnderProtection;
	}
	
	public int getAverageLand(){
		return land / numberOfCountries;
	}
	
	public int compareTo(IComparable anotherObject, int method){
		RankingVO anotherVO = (RankingVO)anotherObject;
		switch(method){
		case SORT_BY_NETWORTH:
			return BasicComparable.compareLong(networth, anotherVO.networth);
		case SORT_BY_LAND:
			return BasicComparable.compareInt(land, anotherVO.land);
		case SORT_BY_AVERAGE_NETWORTH:
			return BasicComparable.compareLong(getAverageNetworth(), anotherVO.getAverageNetworth());
		case SORT_BY_NETWORTH_LAND_RATIO:
			return BasicComparable.compareLong(getNetworthLandRatio(), anotherVO.getNetworthLandRatio());
		case SORT_BY_DEAD_COUNTRIES:
			return BasicComparable.compareInt(deadCountries, anotherVO.deadCountries);
		case SORT_BY_PROTECTION_COUNTRIES :
			return BasicComparable.compareInt(countriesUnderProtection, anotherVO.countriesUnderProtection);
		case SORT_BY_GDI_COUNTRIES:
			return BasicComparable.compareInt(gdiCountries, anotherVO.gdiCountries);
		case SORT_BY_ACTIVE_COUNTRIES :
			return BasicComparable.compareInt(getActiveCountries(), anotherVO.getActiveCountries());
			
		case SORT_BY_TOP10_COUNTRIES:
			return BasicComparable.compareInt(countriesInTop10, anotherVO.countriesInTop10);
		case SORT_BY_TOP25_COUNTRIES :
			return BasicComparable.compareInt(countriesInTop25, anotherVO.countriesInTop25);
		case SORT_BY_TOP50_COUNTRIES :
			return BasicComparable.compareInt(countriesInTop50, anotherVO.countriesInTop50);
		case SORT_BY_TOP100_COUNTRIES :
			return BasicComparable.compareInt(countriesInTop100, anotherVO.countriesInTop100);
		case SORT_BY_AVERAGE_LAND:
			return BasicComparable.compareInt(getAverageLand(), anotherVO.getAverageLand());
		case SORT_BY_TOTAL_COUNTRIES:
			return BasicComparable.compareInt(numberOfCountries, anotherVO.numberOfCountries);
			
		default:
			throw new RuntimeException("Unsupported sort method: "+method);
		
		}
	}

	public String getSortingValue(int method){
		switch(method){
		case SORT_BY_NETWORTH:
			return NumberUtils.getDotedNumber(networth);
		case SORT_BY_LAND:
			return NumberUtils.getDotedNumber(land);
		case SORT_BY_AVERAGE_NETWORTH:
			return NumberUtils.getDotedNumber(getAverageNetworth());
		case SORT_BY_NETWORTH_LAND_RATIO:
			return NumberUtils.getDotedNumber(getNetworthLandRatio());
		case SORT_BY_DEAD_COUNTRIES:
			return ""+deadCountries;
		case SORT_BY_PROTECTION_COUNTRIES :
			return ""+countriesUnderProtection;
		case SORT_BY_GDI_COUNTRIES:
			return ""+gdiCountries;
		case SORT_BY_ACTIVE_COUNTRIES :
			return ""+getActiveCountries();
			
		case SORT_BY_TOP10_COUNTRIES:
			return ""+countriesInTop10;
		case SORT_BY_TOP25_COUNTRIES :
			return ""+countriesInTop25;
		case SORT_BY_TOP50_COUNTRIES :
			return ""+countriesInTop50;
		case SORT_BY_TOP100_COUNTRIES :
			return ""+countriesInTop100;
		case SORT_BY_AVERAGE_LAND:
			return NumberUtils.getDotedNumber(getAverageLand());
		case SORT_BY_TOTAL_COUNTRIES:
			return ""+numberOfCountries;
			
		default:
			return "?";
					
		
		}
	}
}
