package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;

public class KillCounter extends Stat{
		public String describe(){
			return "kills";
		}
		
		public void process(NewsEntry e){
			if (e.isKill())
				increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination());
		}
}
