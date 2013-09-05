package net.anotheria.marsnews.shared;

public class Intervals {
	public static final long HOUR = 1000L*60*60;
	public static final long DAY  = 24*HOUR;
	public static final long WEEK = 7*DAY;
	
	
	public static final long[] INTERVALS = {
		HOUR/2,
		HOUR,
		6*HOUR,
		12*HOUR,
		24*HOUR,
		48*HOUR,
		72*HOUR,
		WEEK,
		2*WEEK,
		3*WEEK,
		4*WEEK,
		System.currentTimeMillis()
	};
	
	public static final String INTERVAL_DESCS[] = {
		"30m",
		"1h",
		"6h",
		"12h",
		"24h",
		"48h",
		"72h",
		"1w",
		"2w",
		"3w",
		"4w",
		"all",
	};
	
	public static final String getDefaultInterval(){
		return "1h";
	}
	
	public static long getIntervalDuration(String intervalName){
		for (int i=0; i<INTERVAL_DESCS.length; i++)
			if (INTERVAL_DESCS[i].equals(intervalName))
				return INTERVALS[i];
		return 6*HOUR;
	}
}
