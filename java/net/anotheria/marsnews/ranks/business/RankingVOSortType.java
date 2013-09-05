package net.anotheria.marsnews.ranks.business;

import net.anotheria.util.sorter.SortType;

public class RankingVOSortType extends SortType{
	public static final int SORT_BY_TOTAL_COUNTRIES = 0;
	public static final int SORT_BY_NETWORTH = 1;
	public static final int SORT_BY_AVERAGE_NETWORTH = 2;
	public static final int SORT_BY_LAND = 3;
	public static final int SORT_BY_AVERAGE_LAND = 4;
	public static final int SORT_BY_NETWORTH_LAND_RATIO = 5;
	public static final int SORT_BY_DEAD_COUNTRIES = 6;
	public static final int SORT_BY_PROTECTION_COUNTRIES = 7;
	public static final int SORT_BY_GDI_COUNTRIES = 8;
	public static final int SORT_BY_ACTIVE_COUNTRIES = 9;
	public static final int SORT_BY_TOP10_COUNTRIES = 10;
	public static final int SORT_BY_TOP25_COUNTRIES = 11;
	public static final int SORT_BY_TOP50_COUNTRIES = 12;
	public static final int SORT_BY_TOP100_COUNTRIES = 13;
	
	
	
	public static final String DESC[] = {
		"Total countries",
		"Total networth",
		"Average networth",
		"Total land",
		"Average land",
		"Networth / Land Ratio",
		"Dead countries",
		"Countries under protection",
		"Countries in GDI",
		"Active countries",
		"Countries in TOP 10",
		"Countries in TOP 25",
		"Countries in TOP 50",
		"Countries in TOP 100",
		
	};
	
	public RankingVOSortType(){
		super(SORT_BY_NETWORTH, false);
	}
	
	public RankingVOSortType(int aSortBy){
		super(aSortBy, false);
	}
	
}
