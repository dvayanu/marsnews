package net.anotheria.marsnews.news.business;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private int id;
	private String name;
	private String currentClan;
	private List<TagChange> tagChanges;
	private long firstOccurence;
	private NewsTotals totals;
	private boolean alive = true;
	
	public Country(){
		totals = new NewsTotals();
	}
	
	
	public Country(int anId, String aName, String aCurrentClan, long aFirstOccurence){
		id = anId;
		name = aName;
		currentClan = aCurrentClan;
		firstOccurence = aFirstOccurence;
		tagChanges = new ArrayList<TagChange>();
		tagChanges.add(new TagChange(currentClan,firstOccurence));
		totals = new NewsTotals();
	}
	
	public void processNewsEntry(NewsEntry e){
		if (e.getAttackerId()==id){
			process(e.getTimestamp(), e.getAttackerClan());
			totals.processAttackEntry(e);
		}
		if (e.getDefenderId()==id){
			process(e.getTimestamp(), e.getDefenderClan());
			totals.processDefenseEntry(e);
			if (e.isKill())
				alive = false;
		}
		
	}

	public boolean isAlive(){
		return alive;
	}

	public void process(long timestamp, String clanName){
		if (tagChanges.get(tagChanges.size()-1).getTag().equals(clanName))
			return;
		tagChanges.add(new TagChange(clanName, timestamp));
	}

	public String getCurrentClan() {
		return currentClan;
	}

	public void setCurrentClan(String currentClan) {
		this.currentClan = currentClan;
	}

	public long getFirstOccurence() {
		return firstOccurence;
	}

	public void setFirstOccurence(long firstOccurence) {
		this.firstOccurence = firstOccurence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TagChange> getTagChanges() {
		return tagChanges;
	}

	public void setTagChanges(List<TagChange> tagChanges) {
		this.tagChanges = tagChanges;
	}
	
	public String toString(){
		return getName()+" (#"+getId()+") ["+getCurrentClan()+"] {"+getTagChanges()+"}";
	}
	
	public NewsTotals getTotals(){
		return totals;
	}
}
