package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.BasicComparable;
import net.anotheria.util.sorter.IComparable;
//import ClansStatsCountryBeanSortType;


public class ClanStatsCountryBean implements IComparable{
	private int id;
	private String name;
	
	private boolean dead;
	
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

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
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

	public long getAttackCountAB() {
		return attackCountAB;
	}

	public void setAttackCountAB(long attackCountAB) {
		this.attackCountAB = attackCountAB;
	}

	public long getAttackCountBR() {
		return attackCountBR;
	}

	public void setAttackCountBR(long attackCountBR) {
		this.attackCountBR = attackCountBR;
	}

	public long getAttackCountCM() {
		return attackCountCM;
	}

	public void setAttackCountCM(long attackCountCM) {
		this.attackCountCM = attackCountCM;
	}

	public long getAttackCountEM() {
		return attackCountEM;
	}

	public void setAttackCountEM(long attackCountEM) {
		this.attackCountEM = attackCountEM;
	}

	public long getAttackCountGS() {
		return attackCountGS;
	}

	public void setAttackCountGS(long attackCountGS) {
		this.attackCountGS = attackCountGS;
	}

	public long getAttackCountNM() {
		return attackCountNM;
	}

	public void setAttackCountNM(long attackCountNM) {
		this.attackCountNM = attackCountNM;
	}

	public long getAttackCountPS() {
		return attackCountPS;
	}

	public void setAttackCountPS(long attackCountPS) {
		this.attackCountPS = attackCountPS;
	}

	public long getAttackCountSS() {
		return attackCountSS;
	}

	public void setAttackCountSS(long attackCountSS) {
		this.attackCountSS = attackCountSS;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}

	public long getDefendCountAB() {
		return defendCountAB;
	}

	public void setDefendCountAB(long defendCountAB) {
		this.defendCountAB = defendCountAB;
	}

	public long getDefendCountBR() {
		return defendCountBR;
	}

	public void setDefendCountBR(long defendCountBR) {
		this.defendCountBR = defendCountBR;
	}

	public long getDefendCountCM() {
		return defendCountCM;
	}

	public void setDefendCountCM(long defendCountCM) {
		this.defendCountCM = defendCountCM;
	}

	public long getDefendCountEM() {
		return defendCountEM;
	}

	public void setDefendCountEM(long defendCountEM) {
		this.defendCountEM = defendCountEM;
	}

	public long getDefendCountGS() {
		return defendCountGS;
	}

	public void setDefendCountGS(long defendCountGS) {
		this.defendCountGS = defendCountGS;
	}

	public long getDefendCountNM() {
		return defendCountNM;
	}

	public void setDefendCountNM(long defendCountNM) {
		this.defendCountNM = defendCountNM;
	}

	public long getDefendCountPS() {
		return defendCountPS;
	}

	public void setDefendCountPS(long defendCountPS) {
		this.defendCountPS = defendCountPS;
	}

	public long getDefendCountSS() {
		return defendCountSS;
	}

	public void setDefendCountSS(long defendCountSS) {
		this.defendCountSS = defendCountSS;
	}
	
	public int compareTo(IComparable anotherComparable, int method){
		ClanStatsCountryBean ab = (ClanStatsCountryBean)anotherComparable;
		
		switch(method){
		case  ClanStatsCountryBeanSortType.SORT_BY_NAME :
			return BasicComparable.compareString(getName(), ab.getName());
		
		case  ClanStatsCountryBeanSortType.SORT_BY_DEAD  :
			return BasicComparable.compareBoolean(ab.isDead(), isDead());
		case  ClanStatsCountryBeanSortType.SORT_BY_KILLS :
			return BasicComparable.compareLong(ab.getKills(), getKills());
		
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_SS :
			return BasicComparable.compareLong(ab.getAttackCountSS(), getAttackCountSS());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_PS :
			return BasicComparable.compareLong(ab.getAttackCountPS(), getAttackCountPS());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_GS :
			return BasicComparable.compareLong(ab.getAttackCountGS(), getAttackCountGS());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_BR :
			return BasicComparable.compareLong(ab.getAttackCountBR(), getAttackCountBR());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_AB :
			return BasicComparable.compareLong(ab.getAttackCountAB(), getAttackCountAB());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_NM :
			return BasicComparable.compareLong(ab.getAttackCountNM(), getAttackCountNM());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_CM :
			return BasicComparable.compareLong(ab.getAttackCountCM(), getAttackCountCM());
		case  ClanStatsCountryBeanSortType.SORT_BY_AC_EM :
			return BasicComparable.compareLong(ab.getAttackCountEM(), getAttackCountEM());

		case  ClanStatsCountryBeanSortType.SORT_BY_DS_SS :
			return BasicComparable.compareLong(ab.getDefendCountSS(), getDefendCountSS());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_PS :
			return BasicComparable.compareLong(ab.getDefendCountPS(), getDefendCountPS());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_GS :
			return BasicComparable.compareLong(ab.getDefendCountGS(), getDefendCountGS());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_BR :
			return BasicComparable.compareLong(ab.getDefendCountBR(), getDefendCountBR());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_AB :
			return BasicComparable.compareLong(ab.getDefendCountAB(), getDefendCountAB());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_NM :
			return BasicComparable.compareLong(ab.getDefendCountNM(), getDefendCountNM());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_CM :
			return BasicComparable.compareLong(ab.getDefendCountCM(), getDefendCountCM());
		case  ClanStatsCountryBeanSortType.SORT_BY_DS_EM :
			return BasicComparable.compareLong(ab.getDefendCountEM(), getDefendCountEM());

			

		
		case  ClanStatsCountryBeanSortType.SORT_BY_ATTACK_COUNT :
			return BasicComparable.compareLong(ab.getAttackCount(), getAttackCount());
		case  ClanStatsCountryBeanSortType.SORT_BY_DEFENSE_COUNT :
			return BasicComparable.compareLong(ab.getDefendCount(), getDefendCount());
			
		}
		System.out.println("Unknown sortMethod: "+method);
		return 0;
	}
	
	
	
}
