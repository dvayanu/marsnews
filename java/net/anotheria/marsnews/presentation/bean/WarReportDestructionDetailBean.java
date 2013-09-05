package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.NumberUtils;

public class WarReportDestructionDetailBean extends BaseWarReportBean{
	private long landTaken;
	private long landDestroyed;
	
	private long buildingsTaken;
	private long buildingsDestroyed;
	
	private long foodDestroyed;
	private long civiliansDestroyed;
	
	public String getBuildingsDestroyed() {
		return NumberUtils.getDotedNumber(buildingsDestroyed);
	}
	public void setBuildingsDestroyed(long buildingsDestroyed) {
		this.buildingsDestroyed = buildingsDestroyed;
	}
	public String getBuildingsTaken() {
		return NumberUtils.getDotedNumber(buildingsTaken);
	}
	public void setBuildingsTaken(long buildingsTaken) {
		this.buildingsTaken = buildingsTaken;
	}
	public String getCiviliansDestroyed() {
		return NumberUtils.getDotedNumber(civiliansDestroyed);
	}
	public void setCiviliansDestroyed(long civiliansDestroyed) {
		this.civiliansDestroyed = civiliansDestroyed;
	}
	public String getFoodDestroyed() {
		return NumberUtils.getDotedNumber(foodDestroyed);
	}
	public void setFoodDestroyed(long foodDestroyed) {
		this.foodDestroyed = foodDestroyed;
	}
	public String getLandDestroyed() {
		return NumberUtils.getDotedNumber(landDestroyed);
	}
	public void setLandDestroyed(long landDestroyed) {
		this.landDestroyed = landDestroyed;
	}
	public String getLandTaken() {
		return NumberUtils.getDotedNumber(landTaken);
	}
	public void setLandTaken(long landTaken) {
		this.landTaken = landTaken;
	}
	
	public String getLandTotal(){
		return NumberUtils.getDotedNumber(landTaken + landDestroyed);
	}
	
	public String getBuildingsTotal(){
		return NumberUtils.getDotedNumber(buildingsTaken + buildingsDestroyed);
	}
}
