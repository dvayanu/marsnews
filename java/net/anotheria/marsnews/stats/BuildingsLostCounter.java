package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class BuildingsLostCounter extends Stat{
		public String describe(){
			return "buildings lost";
		}
		
		public void process(NewsEntry e){
			if (e.getType()==AttackType.PS || e.getType()==AttackType.SS || e.getType()==AttackType.CM)
				increase(e.getDefenderClanForStats(), e.getDefenderId(), e.getDefendClanCombination(), e.getResult2());
			if (e.getType()==AttackType.AB )
				increase(e.getDefenderClanForStats(), e.getDefenderId(), e.getDefendClanCombination(), e.getResult1());
		}
}
