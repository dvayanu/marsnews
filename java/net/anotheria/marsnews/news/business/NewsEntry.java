package net.anotheria.marsnews.news.business;

import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.util.NumberUtils;

public class NewsEntry {
	private int id;
	private int attackerId;
	private String attackerName;
	private String attackerClan;
	
	private int defenderId;
	private String defenderName;
	private String defenderClan;
	
	private AttackType type;
	
	private boolean kill;
	
	private int result1;
	private int result2;
	
	private long timestamp;
	
	public static final String UNTAGED = "UNTAGGED";
	
	public String toString(){
		String ret = "["+id+"] ";
		ret += NumberUtils.makeDigitalDateString(getTimestamp())+" "+NumberUtils.makeTimeString(getTimestamp());
		ret += " "+type+" ";
		ret += makeCountryString(attackerId, attackerName, attackerClan);
		ret += " -> ";
		ret += makeCountryString(defenderId, defenderName, defenderClan);
		ret += " ";
		ret += result1+" / "+result2;
		if (kill)
			ret += " DEAD";
		
		
		return ret;
	}
	
	private String makeCountryString(int id, String name, String clan){
		String ret = name +" ["+id+"]";
		if (clan!=null && clan.length()>0)
			ret += " ("+clan+")";
		return ret;
	}

	public String getAttackerClan() {
		return attackerClan;
	}
	
	public String getAttackerClanForStats(){
		return getAttackerClan() == null || getAttackerClan().length() == 0 ?
				UNTAGED : getAttackerClan();
	}

	public String getDefenderClanForStats(){
		return getDefenderClan() == null || getDefenderClan().length() == 0 ?
				UNTAGED : getDefenderClan();
	}

	public void setAttackerClan(String attackerClan) {
		this.attackerClan = attackerClan;
	}

	public int getAttackerId() {
		return attackerId;
	}

	public void setAttackerId(int attackerId) {
		this.attackerId = attackerId;
	}

	public String getAttackerName() {
		return attackerName;
	}

	public void setAttackerName(String attackerName) {
		this.attackerName = attackerName;
	}

	public String getDefenderClan() {
		return defenderClan;
	}

	public void setDefenderClan(String defenderClan) {
		this.defenderClan = defenderClan;
	}

	public int getDefenderId() {
		return defenderId;
	}

	public void setDefenderId(int defenderId) {
		this.defenderId = defenderId;
	}

	public String getDefenderName() {
		return defenderName;
	}

	public void setDefenderName(String defenderName) {
		this.defenderName = defenderName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isKill() {
		return kill;
	}

	public void setKill(boolean kill) {
		this.kill = kill;
	}

	public int getResult1() {
		return result1;
	}

	public void setResult1(int result1) {
		this.result1 = result1;
	}

	public int getResult2() {
		return result2;
	}

	public void setResult2(int result2) {
		this.result2 = result2;
	}

	public AttackType getType() {
		return type;
	}

	public void setType(AttackType type) {
		this.type = type;
	}
	
	public String getAttackClanCombination(){
		return getAttackerClanForStats()+" vs. "+getDefenderClanForStats();
	}

	public String getDefendClanCombination(){
		return getDefenderClanForStats()+ " vs. "+getAttackerClanForStats();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean isHeld(){
		return getResult1() == 0;
	}
}

