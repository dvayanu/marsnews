package net.anotheria.marsnews.news.business;

public class NewsServiceFactory {
	private static NewsService instance = new NewsServiceImpl();
	
	public static final NewsService getInstance(){
		return instance;
	}
}
