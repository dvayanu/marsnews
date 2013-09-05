package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class EMAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.EM;
	}
	protected String getActionString(){
		return "sent";
	}

}
