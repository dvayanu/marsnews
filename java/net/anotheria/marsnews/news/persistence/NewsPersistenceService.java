package net.anotheria.marsnews.news.persistence;

import java.util.List;

import net.anotheria.marsnews.news.business.NewsEntry;

public interface NewsPersistenceService {
	
	public int getLastNewsEntryId() throws NewsPersistenceServiceException;
	
	public void importNewsEntry(NewsEntry entry) throws NewsPersistenceServiceException;
	
	public void importNewsEntries(List<NewsEntry> entries) throws NewsPersistenceServiceException;
	
	public List<NewsEntry> getAllNewsEntries() throws NewsPersistenceServiceException;
}
