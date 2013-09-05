package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.NumberUtils;

public class RankedCountryBean {
	private int rank;
	private int land;
	private long networth;
	private String name;
	private String clan;
	private int countryId;
	private boolean dead;
	private boolean gdi;
	private boolean underProtection;
	private String goverment;
	private String date;
	
	
	public String getClan() {
		return clan;
	}
	public void setClan(String clan) {
		this.clan = clan;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean isGdi() {
		return gdi;
	}
	public void setGdi(boolean gdi) {
		this.gdi = gdi;
	}
	public String getLand() {
		return NumberUtils.getDotedNumber(land);
	}
	public void setLand(int land) {
		this.land = land;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNetworth() {
		return NumberUtils.getDotedNumber(networth);
	}
	public void setNetworth(long networth) {
		this.networth = networth;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public boolean isUnderProtection() {
		return underProtection;
	}
	public void setUnderProtection(boolean underProtection) {
		this.underProtection = underProtection;
	}
	public String getGoverment() {
		return goverment;
	}
	public void setGoverment(String goverment) {
		this.goverment = goverment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
