package net.anotheria.marsnews.news.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.anotheria.marsnews.shared.AttackType;

public class NewsTotals {
	private long landLost;
	private long landGained;
	private long landDestroyed;
	
	private long buildingsLost;
	private long buildingsGained;
	private long buildingsDestroyed;
	
	private long unitsLost;
	private long unitsKilled;
	
	private long civiliansLost;
	private long civiliansKilled;
	
	private long foodLost;
	private long foodDestroyed;

	private long kills;
	private long deaths;
	
	private Map<Integer,Map<AttackType,AttackTypeCounter>> attackingCountries;
	private Map<Integer,Map<AttackType,AttackTypeCounter>> defendingCountries;
 	
	private Map<Integer,Map<AttackType,AttackTypeCounter>> attackedCountries;
	private Map<Integer,Map<AttackType,AttackTypeCounter>> attackedByCountries;

	private Map<AttackType, AttackTypeCounter> attacks;
	private Map<AttackType, AttackTypeCounter> defends;
	
	public NewsTotals(){
		attacks = new HashMap<AttackType, AttackTypeCounter>();
		defends = new HashMap<AttackType, AttackTypeCounter>();
		
		attackingCountries = new HashMap<Integer,Map<AttackType,AttackTypeCounter>>();
		defendingCountries = new HashMap<Integer,Map<AttackType,AttackTypeCounter>>();
		
		attackedCountries = new HashMap<Integer,Map<AttackType,AttackTypeCounter>>() ;
		attackedByCountries = new HashMap<Integer,Map<AttackType,AttackTypeCounter>>();
	}

	public long getBuildingsDestroyed() {
		return buildingsDestroyed;
	}

	public void setBuildingsDestroyed(long buildingsDestroyed) {
		this.buildingsDestroyed = buildingsDestroyed;
	}

	public long getBuildingsGained() {
		return buildingsGained;
	}

	public void setBuildingsGained(long buildingsGained) {
		this.buildingsGained = buildingsGained;
	}

	public long getBuildingsLost() {
		return buildingsLost;
	}

	public void setBuildingsLost(long buildingsLost) {
		this.buildingsLost = buildingsLost;
	}

	public long getCiviliansKilled() {
		return civiliansKilled;
	}

	public void setCiviliansKilled(long civiliansKilled) {
		this.civiliansKilled = civiliansKilled;
	}

	public long getCiviliansLost() {
		return civiliansLost;
	}

	public void setCiviliansLost(long civiliansLost) {
		this.civiliansLost = civiliansLost;
	}

	public long getFoodDestroyed() {
		return foodDestroyed;
	}

	public void setFoodDestroyed(long foodDestroyed) {
		this.foodDestroyed = foodDestroyed;
	}

	public long getLandDestroyed() {
		return landDestroyed;
	}

	public void setLandDestroyed(long landDestroyed) {
		this.landDestroyed = landDestroyed;
	}

	public long getLandGained() {
		return landGained;
	}

	public void setLandGained(long landGained) {
		this.landGained = landGained;
	}

	public long getLandLost() {
		return landLost;
	}

	public void setLandLost(long landLost) {
		this.landLost = landLost;
	}

	public long getUnitsKilled() {
		return unitsKilled;
	}

	public void setUnitsKilled(long unitsKilled) {
		this.unitsKilled = unitsKilled;
	}

	public long getUnitsLost() {
		return unitsLost;
	}

	public void setUnitsLost(long unitsLost) {
		this.unitsLost = unitsLost;
	}
	
	public long getFoodLost() {
		return foodLost;
	}

	public void setFoodLost(long foodLost) {
		this.foodLost = foodLost;
	}
	
	/////////// INCREMENET METHODS /////////////
	
	public void incrementBuildingsDestroyed(long someBuildingsDestroyed) {
		buildingsDestroyed += someBuildingsDestroyed;
	}

	public void incrementBuildingsGained(long someBuildingsGained) {
		buildingsGained += someBuildingsGained;
	}

	public void incrementBuildingsLost(long someBuildingsLost) {
		buildingsLost += someBuildingsLost;
	}

	public void incrementCiviliansKilled(long someCiviliansKilled) {
		civiliansKilled += someCiviliansKilled;
	}

	public void incrementCiviliansLost(long someCiviliansLost) {
		civiliansLost += someCiviliansLost;
	}

	public void incrementFoodDestroyed(long someFoodDestroyed) {
		foodDestroyed += someFoodDestroyed;
	}

	public void incrementFoodLost(long someFoodLost) {
		foodLost += someFoodLost;
	}

	public void incrementLandDestroyed(long someLandDestroyed) {
		landDestroyed += someLandDestroyed;
	}

	public void incrementLandGained(long someLandGained) {
		landGained += someLandGained;
	}

	public void incrementLandLost(long someLandLost) {
		landLost += someLandLost;
	}

	public void incrementUnitsKilled(long someUnitsKilled) {
		unitsKilled += someUnitsKilled;
	}

	public void incrementUnitsLost(long someUnitsLost) {
		unitsLost += someUnitsLost;
	}
	
	public void incrementKills(long someKills){
		kills += someKills;
	}
	
	public void incrementKills(){
		kills++;
	}
	
	public long getKills(){
		return kills;
	}
	
	public void setKills(long someKills){
		kills = someKills;
	}
	
	public void incrementDeaths(long someDeaths){
		deaths += someDeaths;
	}
	
	public void incrementDeaths(){
		deaths++;
	}
	
	public long getDeaths(){
		return deaths;
	}
	
	public void setDeaths(long someDeaths){
		deaths = someDeaths;
	}
	
	
	public String toString(){
		return "LL: "+getLandLost()+", LG: "+getLandGained()+", LD: "+getLandDestroyed()+
			", BL: "+getBuildingsLost()+", BG: "+getBuildingsGained()+", BD: "+getBuildingsDestroyed()+
			", UL: "+getUnitsLost()+", UK: "+getUnitsKilled()+
			", CL: "+getCiviliansLost()+", CK: "+getCiviliansKilled()+
			", FL: "+getFoodLost()+", FD: "+getFoodDestroyed()+
			", Kills: "+getKills()+", Deaths: "+getDeaths()+"\n"+
			"Attacks: "+attacks.values()+", Defends: "+defends.values()+"\n"+
			"Attackers: "+attackingCountries.size()+", Defenders: "+defendingCountries.size();
	}
	
	public void processDefenseEntry(NewsEntry e){
		if (e.isKill())
			incrementDeaths();
		getCounter(false, e.getType()).increment(e.isHeld());

		addAttackForCountry(defendingCountries, e.getType(), e.getDefenderId(), e.isHeld());
		addAttackForCountry(attackedByCountries, e.getType(), e.getAttackerId(), e.isHeld());

		
		switch(e.getType()){
		case SS:
			incrementLandLost(e.getResult1());
			incrementBuildingsLost(e.getResult2());
			break;
		case PS:
			incrementLandLost(e.getResult1());
			incrementBuildingsLost(e.getResult2());
			break;
		case GS:
			incrementCiviliansLost(e.getResult1());
			incrementFoodLost(e.getResult2());
			break;
		case AB:
			incrementCiviliansLost(e.getResult1());
			incrementBuildingsLost(e.getResult2());
			break;
		case BR:
			incrementCiviliansLost(e.getResult1());
			incrementBuildingsLost(e.getResult2());
			break;
		case NM:
			incrementLandLost(e.getResult1());
			break;
		case CM:
			incrementCiviliansLost(e.getResult1());
			incrementBuildingsLost(e.getResult2());
			break;
		case EM:
			incrementUnitsLost(e.getResult1());
			break;
		default:
			throw new RuntimeException("unsupported attack type: "+e.getType()+", entry: "+e);
		}
	}
	
	private void addAttackForCountry(Map<Integer, Map<AttackType, AttackTypeCounter>> source, AttackType type, int countryId, boolean held){
		Map<AttackType,AttackTypeCounter> attackTypesForCountry = source.get(countryId);
		if (attackTypesForCountry==null){
			attackTypesForCountry = new HashMap<AttackType, AttackTypeCounter>();
			source.put(countryId, attackTypesForCountry);
		}
		AttackTypeCounter attackCounter = attackTypesForCountry.get(type);
		if (attackCounter==null){
			attackCounter = new AttackTypeCounter(type);
			attackTypesForCountry.put(type, attackCounter);
		}
		attackCounter.increment(held);
		
	}

	public void processAttackEntry(NewsEntry e){
		if (e.isKill())
			incrementKills();
		getCounter(true, e.getType()).increment(e.isHeld());
		
		addAttackForCountry(attackingCountries, e.getType(), e.getAttackerId(), e.isHeld());
		addAttackForCountry(attackedCountries, e.getType(), e.getDefenderId(), e.isHeld());
		
		
		switch(e.getType()){
		case SS:
			incrementLandGained(e.getResult1());
			incrementBuildingsGained(e.getResult2());
			break;
		case PS:
			incrementLandGained(e.getResult1());
			incrementBuildingsGained(e.getResult2());
			break;
		case GS:
			incrementCiviliansKilled(e.getResult1());
			incrementFoodDestroyed(e.getResult2());
			break;
		case AB:
			incrementCiviliansKilled(e.getResult1());
			incrementBuildingsDestroyed(e.getResult2());
			break;
		case BR:
			incrementCiviliansKilled(e.getResult1());
			incrementBuildingsDestroyed(e.getResult2());
			break;
		case NM:
			incrementLandDestroyed(e.getResult1());
			break;
		case CM:
			incrementCiviliansKilled(e.getResult1());
			incrementBuildingsDestroyed(e.getResult2());
			break;
		case EM:
			incrementUnitsKilled(e.getResult1());
			break;
		default:
			throw new RuntimeException("unsupported attack type: "+e.getType()+", entry: "+e);
		}
	}
	
	private AttackTypeCounter getCounter(boolean attack, AttackType type){
		Map<AttackType, AttackTypeCounter> map = attack ? attacks : defends;
		AttackTypeCounter ret = map.get(type);
		if (ret ==null){
			ret = new AttackTypeCounter(type);
			map.put(type, ret);
		}
		return ret;
	}
	
	public long getAttackCountForType(AttackType type){
		return getCountForType(attacks, type);
	}
	
	public long getDefenseCountForType(AttackType type){
		return getCountForType(defends, type);
	}
	
	public long getAttackHeldCountForType(AttackType type){
		return getHeldForType(attacks, type);
	}
	
	public long getDefenseHeldCountForType(AttackType type){
		return getHeldForType(defends, type);
	}

	private long getCountForType(Map<AttackType, AttackTypeCounter> map, AttackType type){
		AttackTypeCounter counter = map.get(type);
		return counter == null ? 0L : counter.getCount(); 
	}
	
	private long getHeldForType(Map<AttackType, AttackTypeCounter> map, AttackType type){
		AttackTypeCounter counter = map.get(type);
		return counter == null ? 0L : counter.getHeld();
	}
	
	public void addAll(NewsTotals other){
		landLost += other.landLost;
		landGained += other.landGained;
		landDestroyed += other.landDestroyed;
		
		buildingsLost += other.buildingsLost;
		buildingsGained += other.buildingsGained;
		buildingsDestroyed += other.buildingsDestroyed;
		
		unitsLost += other.unitsLost;
		unitsKilled += other.unitsKilled;
		
		civiliansLost += other.civiliansLost;
		civiliansKilled += other.civiliansKilled;
		
		foodLost += other.foodLost;
		foodDestroyed += other.foodDestroyed;

		kills += other.kills;
		deaths += other.deaths;
		
		addAllInMap(attacks, other.attacks);
		addAllInMap(defends, other.defends);
		
		copyAttackDetailMap(attackingCountries, other.attackingCountries);
		copyAttackDetailMap(attackedCountries, other.attackedCountries);
		copyAttackDetailMap(attackedByCountries, other.attackedByCountries);
		copyAttackDetailMap(defendingCountries, other.defendingCountries);
		
	}
	
	private void copyAttackDetailMap(Map<Integer,Map<AttackType,AttackTypeCounter>> dest, Map<Integer,Map<AttackType,AttackTypeCounter>> source){
		Collection<Integer> sourceCountryKeys = source.keySet();
		for (Integer key : sourceCountryKeys){
			Map<AttackType,AttackTypeCounter> destMap = dest.get(key);
			if (destMap==null){
				dest.put(key, source.get(key));
			}else{
				addAllInMap(destMap, source.get(key));
			}
		}
	}
	
	private void addAllInMap(Map<AttackType, AttackTypeCounter> dest, Map<AttackType, AttackTypeCounter> src){
		for (AttackTypeCounter c : src.values()){
			AttackTypeCounter destCounter = dest.get(c.getType());
			if (destCounter==null){
				destCounter=new AttackTypeCounter(c.getType());
				dest.put(destCounter.getType(), destCounter);
			}
			destCounter.addAll(c);
		}
		
	}
	
	public int getAttackerCount(){
		return attackingCountries.size();
	}
	 
	public int getAttackedCount(){
		return attackedCountries.size();
	}

	public int getDefenderCount(){
		return defendingCountries.size();
	}


	
	public long getTotalAttacks(boolean brokeOnly){
		return getTotalHits(attacks.values(), brokeOnly);
	}
	
	public long getTotalDefends(boolean brokeOnly){
		return getTotalHits(defends.values(), brokeOnly);
	}
	
	private long getTotalHits(Collection<AttackTypeCounter> counters, boolean brokeOnly){
		long ret = 0;
		for (AttackTypeCounter c : counters){
			ret += c.getCount();
			if (brokeOnly)
				ret -= c.getHeld();
		}
		return ret;
	}


	
}
