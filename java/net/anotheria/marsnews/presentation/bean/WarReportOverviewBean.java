package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.NumberUtils;

public class WarReportOverviewBean extends BaseWarReportBean{
	private long totalHits;
	private long successfulHits;
	private long kills;
	private long attackers;
	private long targets;
	
	public long getAttackers() {
		return attackers;
	}
	public void setAttackers(long attackers) {
		this.attackers = attackers;
	}
	public String getHitsPerAttacker() {
		return attackers == 0 ? "-" : NumberUtils.getCurrencyValue((double)totalHits/attackers);
	}
	public long getKills() {
		return kills;
	}
	public void setKills(long kills) {
		this.kills = kills;
	}
	
	public String getKillsPerAttacker() {
		return attackers == 0 ? "-" : NumberUtils.getCurrencyValue((double)kills / attackers);
	}

	public long getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}
	public String getHitsPerKill() {
		return kills == 0 ? "-" : NumberUtils.getCurrencyValue((double)totalHits/kills);
	}
	public long getSuccessfulHits() {
		return successfulHits;
	}
	public void setSuccessfulHits(long successfulHits) {
		this.successfulHits = successfulHits;
	}
	
	public String getEfficency(){
		return NumberUtils.getCurrencyValue((double)successfulHits / totalHits);
	}
	public long getTargets() {
		return targets;
	}
	public void setTargets(long targets) {
		this.targets = targets;
	}
}
