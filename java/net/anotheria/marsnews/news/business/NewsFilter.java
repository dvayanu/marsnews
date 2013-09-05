package net.anotheria.marsnews.news.business;

public interface NewsFilter {
	public boolean mayPass(NewsEntry e);
}
