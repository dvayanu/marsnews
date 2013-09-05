package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.anotheria.marsnews.news.business.CombiNewsFilter;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsServiceException;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.news.business.NewsTotalsUtility;

public class ShowNewsWithCombinedFilterAction extends ShowNewsAction{

	
	@Override
	protected List<NewsEntry> getNewsEntriesSelection(HttpServletRequest req) throws NewsServiceException {
		try{
			CombiNewsFilter f = createFilterFromRequest(req);
			addBeanToRequest(req, "subtitle", "for query: "+f);
			addBeanToRequest(req, "currentFilter", f);
			List<NewsEntry> ret = getNewsService().getFilteredNews(f);
			return ret;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList<NewsEntry>();
	}

	@Override
	protected NewsTotals getTotals(HttpServletRequest req, List<NewsEntry> entries) {
		CombiNewsFilter filter = createFilterFromRequest(req);
		if (filter.getAttackerId()!=0)
			return NewsTotalsUtility.calculateTotalsForCountry(filter.getAttackerId(), entries);
		if (filter.getDefenderId()!=0)
			return NewsTotalsUtility.calculateTotalsForCountry(filter.getDefenderId(), entries);
		if (filter.getAttackerClan()!=null && filter.getAttackerClan().length()>0)
			return NewsTotalsUtility.calculateTotalsForClan(filter.getAttackerClan(), entries);
		if (filter.getDefenderClan()!=null && filter.getDefenderClan().length()>0)
			return NewsTotalsUtility.calculateTotalsForClan(filter.getDefenderClan(), entries);
		return new NewsTotals();
	}

	
	
	
}
