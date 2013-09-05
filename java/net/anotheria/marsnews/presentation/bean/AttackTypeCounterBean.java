package net.anotheria.marsnews.presentation.bean;

import net.anotheria.marsnews.shared.AttackType;

public class AttackTypeCounterBean {
	private String type;
	private long count;
	
	private long brokeCount;
	private long heldCount;

	public AttackTypeCounterBean(AttackType aType){
		type = ""+aType;
	}


	public AttackTypeCounterBean(AttackType aType, long aCount, long aHeldCount){
		type = ""+aType;
		count = aCount;
		setHeldCount(aHeldCount);
	}

	public AttackTypeCounterBean(String aType, long aCount){
		type = ""+aType;
		count = aCount;
	}

	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getBrokeCount() {
		return brokeCount;
	}

	public long getHeldCount() {
		return heldCount;
	}

	public void setHeldCount(long aHeldCount) {
		heldCount = aHeldCount;
		brokeCount = count - heldCount;
	}
	
}
