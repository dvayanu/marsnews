package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class GSDefenseCounter extends DefenseTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.GS;
	}

}
