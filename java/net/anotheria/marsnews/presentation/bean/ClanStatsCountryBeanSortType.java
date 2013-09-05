package net.anotheria.marsnews.presentation.bean;

import net.anotheria.util.sorter.SortType;

public class ClanStatsCountryBeanSortType extends SortType{

	public static final int SORT_BY_NAME = 1;
	
	public static final int SORT_BY_DEAD  = 2;
	public static final int SORT_BY_KILLS = 3;
	
	public static final int SORT_BY_AC_SS = 4;
	public static final int SORT_BY_AC_PS = 5;
	public static final int SORT_BY_AC_GS = 6;
	public static final int SORT_BY_AC_BR = 7;
	public static final int SORT_BY_AC_AB = 8;
	public static final int SORT_BY_AC_NM = 9;
	public static final int SORT_BY_AC_CM = 10;
	public static final int SORT_BY_AC_EM = 11;

	public static final int SORT_BY_DS_SS = 12;
	public static final int SORT_BY_DS_PS = 13;
	public static final int SORT_BY_DS_GS = 14;
	public static final int SORT_BY_DS_BR = 15;
	public static final int SORT_BY_DS_AB = 16;
	public static final int SORT_BY_DS_NM = 17;
	public static final int SORT_BY_DS_CM = 18;
	public static final int SORT_BY_DS_EM = 19;

	
	public static final int SORT_BY_ATTACK_COUNT = 20;
	public static final int SORT_BY_DEFENSE_COUNT = 21;
	
	public static final int SORT_BY_DEFAULT = SORT_BY_NAME;
	
	public static final int[] SORT_BY_ALL = {
		SORT_BY_NAME,
		
		//SORT_BY_DEAD  = 2;
		SORT_BY_KILLS ,
		
		SORT_BY_ATTACK_COUNT ,
		SORT_BY_AC_SS ,
		SORT_BY_AC_PS ,
		SORT_BY_AC_GS ,
		SORT_BY_AC_BR ,
		SORT_BY_AC_AB ,
		SORT_BY_AC_NM ,
		SORT_BY_AC_CM ,
		SORT_BY_AC_EM ,

		SORT_BY_DEFENSE_COUNT ,
		SORT_BY_DS_SS ,
		SORT_BY_DS_PS ,
		SORT_BY_DS_GS ,
		SORT_BY_DS_BR ,
		SORT_BY_DS_AB ,
		SORT_BY_DS_NM ,
		SORT_BY_DS_CM ,
		SORT_BY_DS_EM ,

		
	};
	
	public static final String[] CAPTIONS = {
		"Name",
		
		//SORT_BY_DEAD  = 2;
		"Kills",
		
		"Total Att",
		"SS" ,
		"PS" ,
		"GS" ,
		"BR" ,
		"AB", 
		"NM",
		"CM",
		"EM",

		"Total Def",
		"SS" ,
		"PS" ,
		"GS" ,
		"BR" ,
		"AB", 
		"NM",
		"CM",
		"EM",

		
		
	};

	public ClanStatsCountryBeanSortType(){
		super(SORT_BY_DEFAULT);
	}
	
	public ClanStatsCountryBeanSortType(int aSortBy){
		super(aSortBy);
	}

}
