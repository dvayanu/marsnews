package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class LandDestroyedCounter extends Stat{
	public String describe(){
		return "land destroyed";
	}
	
	public void process(NewsEntry e){
		if (e.getType()==AttackType.NM )
			increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination(), e.getResult1());
	}

	
}
