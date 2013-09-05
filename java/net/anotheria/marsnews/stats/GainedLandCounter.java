package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class GainedLandCounter extends Stat{
	public String describe(){
		return "land gained";
	}
	
	public void process(NewsEntry e){
		if (e.getType()==AttackType.PS || e.getType()==AttackType.SS)
			increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination(), e.getResult1());
	}

	
}
