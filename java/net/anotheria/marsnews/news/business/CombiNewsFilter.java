package net.anotheria.marsnews.news.business;

import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.util.NumberUtils;

public class CombiNewsFilter implements NewsFilter{

	private AttackType type;
	private int attackerId;
	private int defenderId;
	private String attackerClan;
	private String defenderClan;
	private boolean killOnly;
	private boolean heldOnly;
	private boolean brokeOnly;
	private long timestampSince;
	
	
	public boolean mayPass(NewsEntry e) {
		if (type!=null)
			if (e.getType()!=type)
				return false;
		if (e.getTimestamp()<timestampSince)
			return false;
		if (attackerId!=0 && attackerId!=e.getAttackerId())
			return false;
		if (defenderId!=0 && defenderId!=e.getDefenderId())
			return false;
		if (killOnly && !e.isKill())
			return false;
		if (heldOnly && !e.isHeld())
			return false;
		if (brokeOnly && e.isHeld())
			return false;
		
		if (attackerClan!=null)
			if (e.getAttackerClan()==null || !e.getAttackerClan().equals(attackerClan))
				return false;

		if (defenderClan!=null)
			if (e.getDefenderClan()==null || !e.getDefenderClan().equals(defenderClan))
				return false;

		return true;
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


	public boolean isBrokeOnly() {
		return brokeOnly;
	}


	public void setBrokeOnly(boolean brokeOnly) {
		this.brokeOnly = brokeOnly;
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


	public boolean isHeldOnly() {
		return heldOnly;
	}


	public void setHeldOnly(boolean heldOnly) {
		this.heldOnly = heldOnly;
	}


	public boolean isKillOnly() {
		return killOnly;
	}


	public void setKillOnly(boolean killOnly) {
		this.killOnly = killOnly;
	}


	public AttackType getType() {
		return type;
	}


	public void setType(AttackType type) {
		this.type = type;
	}


	public long getTimestampSince() {
		return timestampSince;
	}


	public void setTimestampSince(long timestampSince) {
		this.timestampSince = timestampSince;
	}

	public String toString(){
		String ret = "";
		
		ret += "From "+NumberUtils.makeDigitalDateString(getTimestampSince())+" "+NumberUtils.makeTimeString(getTimestampSince());
		if (type!=null)
			ret += ", "+type+" only";

		if (attackerId!=0 )
			ret += ", attacked by "+attackerId;

		if (defenderId!=0 )
			ret += ", defended by "+defenderId;

		if (attackerClan!=null)
			ret += ", attacked by "+attackerClan;
		
		if (defenderClan!=null)
			ret += ", defended by "+defenderClan;
		
		if (killOnly)
			ret += ", only kills";
		
		if (heldOnly)
			ret += ", only def held";

		if (brokeOnly)
			ret += ", only successful attacks";
		
		return ret;
	}
	
}
