package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public abstract class AttackTypeCounter extends Stat{

	@Override
	public String describe() {
		return ""+getType()+" "+getActionString();
	}

	@Override
	public void process(NewsEntry entry) {
		if (entry.getType()==getType())
			increase(entry.getAttackerClanForStats(), entry.getAttackerId(), entry.getAttackClanCombination());
	}
	
	protected abstract AttackType getType();

	protected String getActionString(){
		return "executed";
	}
}
