package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class ABDefenseCounter extends DefenseTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.AB;
	}

}
