package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;

public class DefenseCounter extends Stat{
	public String describe(){
		return "defends";
	}
	
	public void process(NewsEntry e){
		increase(e.getDefenderClanForStats(), e.getDefenderId(), e.getDefendClanCombination());
	}

}
