package net.anotheria.marsnews.shared;

public class AttackTypeHelper {
	
	public static final int _SS = 1;
	public static final int _PS = 2;
	public static final int _GS = 3;
	public static final int _BR = 4;
	public static final int _AB = 5;
	public static final int _MISSILE = 6;
	
	public static final int _NM = 11;
	public static final int _CM = 12;
	public static final int _EM = 13;


	public static final AttackType TYPES[] = {
		AttackType.SS,
		AttackType.PS,
		AttackType.GS,
		AttackType.BR,
		AttackType.AB,
		AttackType.NM,
		AttackType.CM,
		AttackType.EM,
	};
	
	
	
	public static AttackType int2type(int aValue){
		switch(aValue){
		case _SS:
			return AttackType.SS;
		case _PS:
			return AttackType.PS;
		case _GS:
			return AttackType.GS;
		case _BR:
			return AttackType.BR;
		case _AB:
			return AttackType.AB;
		case _EM:
			return AttackType.EM;
		case _NM:
			return AttackType.NM;
		case _CM:
			return AttackType.CM;
		default:
			System.out.println("don't know how to handle: "+aValue);
			return AttackType.UNKNOWN;
		}
	}

	public static int type2int(AttackType type){
		switch(type){
		case SS:
			return _SS;
		case PS:
			return _PS;
		case GS:
			return _GS;
		case BR:
			return _BR;
		case AB:
			return _AB;
		case EM:
			return _EM;
		case NM:
			return _NM;
		case CM:
			return _CM;
		default:
			System.out.println("don't know how to handle: "+type);
			return -1;
		}
	}
}
