package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class BuildingsDestroyedCounter extends Stat{
		public String describe(){
			return "buildings destroyed";
		}
		
		public void process(NewsEntry e){
			if (e.getType()==AttackType.CM)
				increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination(), e.getResult2());
			if (e.getType()==AttackType.AB )
				increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination(), e.getResult1());
		}
}
