package net.anotheria.marsnews.presentation.bean;

public class WarReportAttackDetailBean extends BaseWarReportBean{ 
	private long ss;
	private long ps;
	private long gs;
	private long br;
	private long ab;
	private long nm;
	private long cm;
	private long em;
	
	public long getAb() {
		return ab;
	}
	public void setAb(long ab) {
		this.ab = ab;
	}
	public long getBr() {
		return br;
	}
	public void setBr(long br) {
		this.br = br;
	}
	public long getCm() {
		return cm;
	}
	public void setCm(long cm) {
		this.cm = cm;
	}
	public long getEm() {
		return em;
	}
	public void setEm(long em) {
		this.em = em;
	}
	public long getGs() {
		return gs;
	}
	public void setGs(long gs) {
		this.gs = gs;
	}
	public long getNm() {
		return nm;
	}
	public void setNm(long nm) {
		this.nm = nm;
	}
	public long getPs() {
		return ps;
	}
	public void setPs(long ps) {
		this.ps = ps;
	}
	public long getSs() {
		return ss;
	}
	public void setSs(long ss) {
		this.ss = ss;
	}
	
	public long getTotal(){
		return ss+ps+gs+br+ab+nm+cm+em;
	}
}
