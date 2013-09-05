package net.anotheria.marsnews.news.business;

public class Clan {
	private String name;
	private NewsTotals totals;
	
	public Clan(String aName){
		name = aName;
		totals = new NewsTotals();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NewsTotals getTotals() {
		return totals;
	}
	public void setTotals(NewsTotals totals) {
		this.totals = totals;
	}
	
	public void processNewsEntry(NewsEntry e){
		if (e.getAttackerClan().equals(getName()))
			totals.processAttackEntry(e);
		if (e.getDefenderClan().equals(getName()))
			totals.processDefenseEntry(e);
		
	}
}
