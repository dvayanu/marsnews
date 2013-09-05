package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;

public abstract class DefenseTypeCounter extends Stat{

	@Override
	public String describe() {
		return ""+getType()+" defended";
	}

	@Override
	public void process(NewsEntry entry) {
		if (entry.getType()==getType())
			increase(entry.getDefenderClanForStats(), entry.getDefenderId(), entry.getDefendClanCombination());
	}
	
	protected abstract AttackType getType();
	
}
