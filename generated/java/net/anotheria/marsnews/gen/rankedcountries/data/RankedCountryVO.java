/**
 ********************************************************************************
 *** The implementation of the RankedCountry..java                            ***
 *** Generator: net.anotheria.asg.generator.model.db.VOGenerator              ***
 *** generated by AnoSiteGenerator (ASG), Version: 1.3.3                      ***
 *** Copyright (C) 2005 - 2010 Anotheria.net, www.anotheria.net               ***
 *** All Rights Reserved.                                                     ***
 ********************************************************************************
 *** Don't edit this code, if you aren't sure                                 ***
 *** that you do exactly know what you are doing!                             ***
 *** It's better to invest time in the generator, as into the generated code. ***
 ********************************************************************************
 */

package net.anotheria.marsnews.gen.rankedcountries.data;

import net.anotheria.asg.data.AbstractVO;
import net.anotheria.util.crypt.MD5Util;
import java.io.Serializable;
import net.anotheria.util.sorter.IComparable;
import net.anotheria.util.BasicComparable;

public class RankedCountryVO extends AbstractVO implements RankedCountry, Serializable, IComparable, Comparable<RankedCountry>{

	private String id;
	private int rank;
	private String countryname;
	private int countryid;
	private int land;
	private int networth;
	private int color;
	private String clan;
	private int gov;
	private boolean gdi;
	private boolean dead;
	private boolean protection;
	private long daocreated;
	private long daoupdated;

	public RankedCountryVO(String anId){
		id = anId;
	}

	public RankedCountryVO(RankedCountryVO toClone){
		this.id = toClone.id;
		copyAttributesFrom(toClone);
	}

	RankedCountryVO(RankedCountryBuilder builder){
		id = "";
		rank = builder.rank;
		countryname = builder.countryName;
		countryid = builder.countryId;
		land = builder.land;
		networth = builder.networth;
		color = builder.color;
		clan = builder.clan;
		gov = builder.gov;
		gdi = builder.gdi;
		dead = builder.dead;
		protection = builder.protection;
	}

	public String getId(){
		return id;
	}
	public int getRank(){
		return rank;
	}

	public void setRank(int value){
		this.rank = value;
	}

	public String getCountryName(){
		return countryname;
	}

	public void setCountryName(String value){
		this.countryname = value;
	}

	public int getCountryId(){
		return countryid;
	}

	public void setCountryId(int value){
		this.countryid = value;
	}

	public int getLand(){
		return land;
	}

	public void setLand(int value){
		this.land = value;
	}

	public int getNetworth(){
		return networth;
	}

	public void setNetworth(int value){
		this.networth = value;
	}

	public int getColor(){
		return color;
	}

	public void setColor(int value){
		this.color = value;
	}

	public String getClan(){
		return clan;
	}

	public void setClan(String value){
		this.clan = value;
	}

	public int getGov(){
		return gov;
	}

	public void setGov(int value){
		this.gov = value;
	}

	public boolean getGdi(){
		return gdi;
	}

	public void setGdi(boolean value){
		this.gdi = value;
	}

	public boolean getDead(){
		return dead;
	}

	public void setDead(boolean value){
		this.dead = value;
	}

	public boolean getProtection(){
		return protection;
	}

	public void setProtection(boolean value){
		this.protection = value;
	}

	public long getDaoCreated(){
		return daocreated;
	}
	public void setDaoCreated(long value){
		this.daocreated = value;
	}
	public long getDaoUpdated(){
		return daoupdated;
	}
	public void setDaoUpdated(long value){
		this.daoupdated = value;
	}

	public String toString(){
		String ret = "RankedCountry ";
		ret += "["+getId()+"] ";
		ret += "rank: "+getRank();
		ret += ", ";
		ret += "countryName: "+getCountryName();
		ret += ", ";
		ret += "countryId: "+getCountryId();
		ret += ", ";
		ret += "land: "+getLand();
		ret += ", ";
		ret += "networth: "+getNetworth();
		ret += ", ";
		ret += "color: "+getColor();
		ret += ", ";
		ret += "clan: "+getClan();
		ret += ", ";
		ret += "gov: "+getGov();
		ret += ", ";
		ret += "gdi: "+getGdi();
		ret += ", ";
		ret += "dead: "+getDead();
		ret += ", ";
		ret += "protection: "+getProtection();
		return ret;
	}

	public RankedCountryVO clone(){
		return (RankedCountryVO) super.clone();
	}

	public void copyAttributesFrom(RankedCountry toCopy){
		this.rank = toCopy.getRank();
		this.countryname = toCopy.getCountryName();
		this.countryid = toCopy.getCountryId();
		this.land = toCopy.getLand();
		this.networth = toCopy.getNetworth();
		this.color = toCopy.getColor();
		this.clan = toCopy.getClan();
		this.gov = toCopy.getGov();
		this.gdi = toCopy.getGdi();
		this.dead = toCopy.getDead();
		this.protection = toCopy.getProtection();
	}

	public Object getPropertyValue(String propertyName){
		if (PROP_ID.equals(propertyName))
			return getId();
		if (PROP_RANK.equals(propertyName))
			return getRank();
		if (PROP_COUNTRY_NAME.equals(propertyName))
			return getCountryName();
		if (PROP_COUNTRY_ID.equals(propertyName))
			return getCountryId();
		if (PROP_LAND.equals(propertyName))
			return getLand();
		if (PROP_NETWORTH.equals(propertyName))
			return getNetworth();
		if (PROP_COLOR.equals(propertyName))
			return getColor();
		if (PROP_CLAN.equals(propertyName))
			return getClan();
		if (PROP_GOV.equals(propertyName))
			return getGov();
		if (PROP_GDI.equals(propertyName))
			return getGdi();
		if (PROP_DEAD.equals(propertyName))
			return getDead();
		if (PROP_PROTECTION.equals(propertyName))
			return getProtection();
		throw new RuntimeException("No property getter for "+propertyName);
	}


	public int compareTo(RankedCountry comparable){
		return compareTo(comparable, RankedCountrySortType.SORT_BY_DEFAULT);
	}

	public int compareTo(IComparable anotherComparable, int method){
		RankedCountryVO anotherDoc = (RankedCountryVO) anotherComparable;
		switch(method){
			case RankedCountrySortType.SORT_BY_ID:
				return BasicComparable.compareString(getId(), anotherDoc.getId());
			case RankedCountrySortType.SORT_BY_RANK:
				return BasicComparable.compareInt(getRank(), anotherDoc.getRank());
			case RankedCountrySortType.SORT_BY_COUNTRYNAME:
				return BasicComparable.compareString(getCountryName(), anotherDoc.getCountryName());
			case RankedCountrySortType.SORT_BY_COUNTRYID:
				return BasicComparable.compareInt(getCountryId(), anotherDoc.getCountryId());
			case RankedCountrySortType.SORT_BY_LAND:
				return BasicComparable.compareInt(getLand(), anotherDoc.getLand());
			case RankedCountrySortType.SORT_BY_NETWORTH:
				return BasicComparable.compareInt(getNetworth(), anotherDoc.getNetworth());
			case RankedCountrySortType.SORT_BY_COLOR:
				return BasicComparable.compareInt(getColor(), anotherDoc.getColor());
			case RankedCountrySortType.SORT_BY_CLAN:
				return BasicComparable.compareString(getClan(), anotherDoc.getClan());
			case RankedCountrySortType.SORT_BY_GOV:
				return BasicComparable.compareInt(getGov(), anotherDoc.getGov());
			case RankedCountrySortType.SORT_BY_GDI:
				return BasicComparable.compareBoolean(getGdi(), anotherDoc.getGdi());
			case RankedCountrySortType.SORT_BY_DEAD:
				return BasicComparable.compareBoolean(getDead(), anotherDoc.getDead());
			case RankedCountrySortType.SORT_BY_PROTECTION:
				return BasicComparable.compareBoolean(getProtection(), anotherDoc.getProtection());
			default:
				throw new RuntimeException("Sort method "+method+" is not supported.");
		}
	}

	public String getDefinedName(){
		return "RankedCountry";
	}

	public String getDefinedParentName(){
		return "RankedCountries";
	}

	public String getFootprint(){
		StringBuilder footprint = new StringBuilder();
		footprint.append(getRank());
		footprint.append(getCountryName());
		footprint.append(getCountryId());
		footprint.append(getLand());
		footprint.append(getNetworth());
		footprint.append(getColor());
		footprint.append(getClan());
		footprint.append(getGov());
		footprint.append(getGdi());
		footprint.append(getDead());
		footprint.append(getProtection());
		return MD5Util.getMD5Hash(footprint);
	}

	public boolean equals(Object o){
		return o == this || ((o instanceof RankedCountryVO) && ((RankedCountryVO)o).getId().equals(getId()));
	}
}
