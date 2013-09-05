package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class ABAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.AB;
	}

}
