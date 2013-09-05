package net.anotheria.marsnews.ranks.business;


public class RankServiceFactory {
	private static volatile RanksService instance;
	
	private static Object lock = new Object();
	
	public static final RanksService createRanksService(){
		if (instance==null){
			synchronized(lock){
				if (instance==null)
					instance = new RanksServiceImpl();
			}
		}
		return instance;
	}
}
