package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class GSAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.GS;
	}

}
