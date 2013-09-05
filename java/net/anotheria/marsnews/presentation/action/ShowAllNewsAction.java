package net.anotheria.marsnews.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsServiceException;
import net.anotheria.marsnews.news.business.NewsTotals;

public class ShowAllNewsAction extends ShowNewsAction{

	@Override
	protected List<NewsEntry> getNewsEntriesSelection(HttpServletRequest req) throws NewsServiceException{
		return getNewsService().getNews(System.currentTimeMillis()-getCurrentIntervalLength(req));
	}

	protected NewsTotals getTotals(HttpServletRequest req, List<NewsEntry> entries) {
		// TODO Auto-generated method stub
		return new NewsTotals();
	}

}
