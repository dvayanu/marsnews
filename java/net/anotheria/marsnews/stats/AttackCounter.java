package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;

public class AttackCounter extends Stat{
	public String describe(){
		return "attacks";
	}
	
	public void process(NewsEntry e){
		increase(e.getAttackerClanForStats(), e.getAttackerId(), e.getAttackClanCombination());
	}
}
