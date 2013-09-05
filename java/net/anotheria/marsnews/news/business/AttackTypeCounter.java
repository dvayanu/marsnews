package net.anotheria.marsnews.news.business;

import net.anotheria.marsnews.shared.AttackType;

public class AttackTypeCounter {
	private AttackType type;
	private long count;
	private long held;
	
	public AttackTypeCounter(AttackType aType){
		type = aType;
	}
	
	public String toString(){
		return type+": "+count;
	}
	
	public long getCount(){
		return count;
	}
	
	public void increment(){
		count++;
	}
	
	public void incrementHeld(){
		held++;
	}
	
	public void increment(boolean aHeld){
		count++;
		if (aHeld)
			held++;
	}
	
	public AttackType getType(){
		return type;
	}

	public long getHeld() {
		return held;
	}
	
	public void addAll(AttackTypeCounter anotherCounter){
		count += anotherCounter.count;
		held  += anotherCounter.held;
	}
}
