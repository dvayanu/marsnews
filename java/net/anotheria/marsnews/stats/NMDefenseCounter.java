package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class NMDefenseCounter extends DefenseTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.NM;
	}

}
