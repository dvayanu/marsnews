package net.anotheria.marsnews.presentation.bean;

public class AttackStatsTotalsBean {
	private long deads;
	
	private long kills;
	
	private long attackCountSS;
	private long attackCountPS;
	private long attackCountGS;
	private long attackCountBR;
	private long attackCountAB;
	private long attackCountNM;
	private long attackCountCM;
	private long attackCountEM;

	private long defendCountSS;
	private long defendCountPS;
	private long defendCountGS;
	private long defendCountBR;
	private long defendCountAB;
	private long defendCountNM;
	private long defendCountCM;
	private long defendCountEM;
	
	public long getAttackCountAB() {
		return attackCountAB;
	}
	public void addAttackCountAB(long attackCountAB) {
		this.attackCountAB += attackCountAB;
	}
	public long getAttackCountBR() {
		return attackCountBR;
	}
	public void addAttackCountBR(long attackCountBR) {
		this.attackCountBR += attackCountBR;
	}
	public long getAttackCountCM() {
		return attackCountCM;
	}
	public void addAttackCountCM(long attackCountCM) {
		this.attackCountCM+= attackCountCM;
	}
	public long getAttackCountEM() {
		return attackCountEM;
	}
	public void addAttackCountEM(long attackCountEM) {
		this.attackCountEM+= attackCountEM;
	}
	public long getAttackCountGS() {
		return attackCountGS;
	}
	public void addAttackCountGS(long attackCountGS) {
		this.attackCountGS+= attackCountGS;
	}
	public long getAttackCountNM() {
		return attackCountNM;
	}
	public void addAttackCountNM(long attackCountNM) {
		this.attackCountNM+= attackCountNM;
	}
	public long getAttackCountPS() {
		return attackCountPS;
	}
	public void addAttackCountPS(long attackCountPS) {
		this.attackCountPS+= attackCountPS;
	}
	public long getAttackCountSS() {
		return attackCountSS;
	}
	public void addAttackCountSS(long attackCountSS) {
		this.attackCountSS+= attackCountSS;
	}
	public long getDeads() {
		return deads;
	}
	public void addDeads(long deads) {
		this.deads += deads;
	}
	public long getDefendCountAB() {
		return defendCountAB;
	}
	public void addDefendCountAB(long defendCountAB) {
		this.defendCountAB += defendCountAB;
	}
	public long getDefendCountBR() {
		return defendCountBR;
	}
	public void addDefendCountBR(long defendCountBR) {
		this.defendCountBR += defendCountBR;
	}
	public long getDefendCountCM() {
		return defendCountCM;
	}
	public void addDefendCountCM(long defendCountCM) {
		this.defendCountCM += defendCountCM;
	}
	public long getDefendCountEM() {
		return defendCountEM;
	}
	public void addDefendCountEM(long defendCountEM) {
		this.defendCountEM += defendCountEM;
	}
	public long getDefendCountGS() {
		return defendCountGS;
	}
	public void addDefendCountGS(long defendCountGS) {
		this.defendCountGS += defendCountGS;
	}
	public long getDefendCountNM() {
		return defendCountNM;
	}
	public void addDefendCountNM(long defendCountNM) {
		this.defendCountNM += defendCountNM;
	}
	public long getDefendCountPS() {
		return defendCountPS;
	}
	public void addDefendCountPS(long defendCountPS) {
		this.defendCountPS += defendCountPS;
	}
	public long getDefendCountSS() {
		return defendCountSS;
	}
	public void addDefendCountSS(long defendCountSS) {
		this.defendCountSS += defendCountSS;
	}
	public long getKills() {
		return kills;
	}
	public void addKills(long kills) {
		this.kills += kills;
	}
	public long getAttackCount(){
		return attackCountSS + 
			attackCountPS+
			attackCountGS+
			attackCountBR+
			attackCountAB+
			attackCountNM+
			attackCountCM+
			attackCountEM;
	}
	
	public long getDefendCount(){
		return defendCountSS + 
			defendCountPS+
			defendCountGS+
			defendCountBR+
			defendCountAB+
			defendCountNM+
			defendCountCM+
			defendCountEM;
	}


}
