package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class CiviliansLostCounter extends Stat{
		public String describe(){
			return "civilians lost";
		}
		
		public void process(NewsEntry e){
			if (e.getType()==AttackType.BR || e.getType()==AttackType.GS || e.getType()==AttackType.CM)
				increase(e.getDefenderClanForStats(), e.getDefenderId(), e.getDefendClanCombination(), e.getResult1());
		}
}
