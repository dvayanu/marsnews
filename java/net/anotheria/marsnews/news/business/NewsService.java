package net.anotheria.marsnews.news.business;

import java.util.List;


public interface NewsService {
	public List<NewsEntry> getNews(long since) throws NewsServiceException;
	
	public List<NewsEntry> getNewsForCountry(int countryId, long since) throws NewsServiceException;
	
	public List<NewsEntry> getNewsForClan(String clan, long since) throws NewsServiceException;
	
	public List<NewsEntry> getFilteredNews(NewsFilter filter) throws NewsServiceException;

	public NewsEntry getNewestNewsEntry() throws NewsServiceException;
	
	public void importNewsEntries(List<NewsEntry> entries) throws NewsServiceException;

	
	public long getLastUpdate() throws NewsServiceException;
	
	public void addListener(NewsServiceListener listener);
	public void removeListener(NewsServiceListener listener);
}
