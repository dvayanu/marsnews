package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public class CiviliansKilledCounter extends Stat{
		public String describe(){
			return "civilians killed";
		}
		
		public void process(NewsEntry e){
			if (e.getType()==AttackType.BR || e.getType()==AttackType.GS || e.getType()==AttackType.CM)
				increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination(), e.getResult1());
		}
}
