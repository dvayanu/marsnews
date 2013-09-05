package net.anotheria.marsnews.stats;

import net.anotheria.marsnews.shared.AttackType;

public class NMAttackCounter extends AttackTypeCounter{

	@Override
	protected AttackType getType() {
		return AttackType.NM;
	}
	protected String getActionString(){
		return "sent";
	}

}
