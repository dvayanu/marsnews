package net.anotheria.marsnews.presentation.bean;

public class NewsItemBean {
	private String date;
	private String attacker;
	private int attackerId;
	private String defender;
	private int defenderId;
	private String type;
	private String result;
	private String attackerClan;
	private String defenderClan;
	
	private String obfuscatedId;
	
	private boolean kill;
	private boolean held;
	public String getAttacker() {
		return attacker;
	}
	public void setAttacker(String attacker) {
		this.attacker = attacker;
	}
	public String getAttackerClan() {
		return attackerClan;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDefender() {
		return defender;
	}
	public void setDefender(String defender) {
		this.defender = defender;
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
	public boolean isHeld() {
		return held;
	}
	public void setHeld(boolean held) {
		this.held = held;
	}
	public boolean isKill() {
		return kill;
	}
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isAttackerTagged(){
		return attackerClan!=null && attackerClan.length()>0;
	}
	public boolean isDefenderTagged(){
		return defenderClan!=null && defenderClan.length()>0;
	}
	public String getObfuscatedId() {
		return obfuscatedId;
	}
	public void setObfuscatedId(String obfuscatedId) {
		this.obfuscatedId = obfuscatedId;
	}
}
