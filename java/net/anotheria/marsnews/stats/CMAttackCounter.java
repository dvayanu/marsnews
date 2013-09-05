package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class CMAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.CM;
	}

	protected String getActionString(){
		return "sent";
	}
}
