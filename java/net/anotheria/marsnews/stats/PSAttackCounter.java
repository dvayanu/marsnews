package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class PSAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.PS;
	}

}
