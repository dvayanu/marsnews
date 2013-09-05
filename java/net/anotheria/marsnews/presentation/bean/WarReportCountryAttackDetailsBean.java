package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.NumberUtils;

public class WarReportCountryAttackDetailsBean {
	
	private String countryDescription;
	private String damage;
	
	private long ss;
	private long ps;
	private long gs;
	private long br;
	private long ab;
	private long nm;
	private long cm;
	private long em;

	private long ssBroke;
	private long psBroke;
	private long gsBroke;
	private long brBroke;
	private long abBroke;
	private long nmBroke;
	private long cmBroke;
	private long emBroke;
	
	private long kills;
	private boolean dead;
	
	public long getTotal(){
		return ss+ps+gs+br+ab+nm+cm+em;
	}
	
	public long getTotalBroke(){
		return ssBroke+psBroke+gsBroke+brBroke+abBroke+nmBroke+cmBroke+emBroke;
	}
	
	public String getKillsDesc(){
		return kills == 0 ? "" : NumberUtils.getDotedNumber(kills);
	}

	public String getAbDesc(){
		return getAttacks(ab, abBroke);
	}

	public String getBrDesc(){
		return getAttacks(br, brBroke);
		
	}

	public String getGsDesc(){
		return getAttacks(gs, gsBroke);
		
	}

	public String getSsDesc(){
		return getAttacks(ss, ssBroke);
		
	}

	public String getTotalDesc(){
		return getAttacks(getTotal(), getTotalBroke());
		
	}

	public String getPsDesc(){
		return getAttacks(ps, psBroke);
		
	}

	public String getNmDesc(){
		return getAttacks(nm, nmBroke);
		
	}

	public String getCmDesc(){
		return getAttacks(cm, cmBroke);
		
	}

	public String getEmDesc(){
		return getAttacks(em, emBroke);
		
	}

	public long getAb() {
		return ab;
	}

	public void setAb(long ab) {
		this.ab = ab;
	}

	public long getAbBroke() {
		return abBroke;
	}

	public void setAbBroke(long abBroke) {
		this.abBroke = abBroke;
	}

	
	public long getBr() {
		return br;
	}

	public void setBr(long br) {
		this.br = br;
	}

	public long getBrBroke() {
		return brBroke;
	}

	public void setBrBroke(long brBroke) {
		this.brBroke = brBroke;
	}

	public long getCm() {
		return cm;
	}

	public void setCm(long cm) {
		this.cm = cm;
	}

	public long getCmBroke() {
		return cmBroke;
	}

	public void setCmBroke(long cmBroke) {
		this.cmBroke = cmBroke;
	}

	public long getEm() {
		return em;
	}

	public void setEm(long em) {
		this.em = em;
	}

	public long getEmBroke() {
		return emBroke;
	}

	public void setEmBroke(long emBroke) {
		this.emBroke = emBroke;
	}

	public long getGs() {
		return gs;
	}

	public void setGs(long gs) {
		this.gs = gs;
	}

	public long getGsBroke() {
		return gsBroke;
	}

	public void setGsBroke(long gsBroke) {
		this.gsBroke = gsBroke;
	}

	public long getNm() {
		return nm;
	}

	public void setNm(long nm) {
		this.nm = nm;
	}

	public long getNmBroke() {
		return nmBroke;
	}

	public void setNmBroke(long nmBroke) {
		this.nmBroke = nmBroke;
	}

	public long getPs() {
		return ps;
	}

	public void setPs(long ps) {
		this.ps = ps;
	}

	public long getPsBroke() {
		return psBroke;
	}

	public void setPsBroke(long psBroke) {
		this.psBroke = psBroke;
	}

	public long getSs() {
		return ss;
	}

	public void setSs(long ss) {
		this.ss = ss;
	}

	public long getSsBroke() {
		return ssBroke;
	}

	public void setSsBroke(long ssBroke) {
		this.ssBroke = ssBroke;
	}

	public String getCountryDescription() {
		return countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String lost) {
		this.damage = lost;
	}
	
	private String getAttacks(long attacks, long broke){
		if (broke==0 && attacks==0)
			return " ";
		if (broke==0)
			return "- / "+attacks;
		return broke +" / "+attacks;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
