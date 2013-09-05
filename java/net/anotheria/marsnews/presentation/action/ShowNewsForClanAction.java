package net.anotheria.marsnews.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsServiceException;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.news.business.NewsTotalsUtility;


public class ShowNewsForClanAction extends ShowNewsAction{

	@Override
	protected List<NewsEntry> getNewsEntriesSelection(HttpServletRequest req) throws NewsServiceException {
		String clanId = getClanId(req);
		addBeanToRequest(req, "subtitle", "for clan "+clanId);
		addBeanToRequest(req, "filterDefenderParam", "pDefClan="+clanId);
		addBeanToRequest(req, "filterAttackerParam", "pAttClan="+clanId);
		return getNewsService().getNewsForClan(clanId, System.currentTimeMillis()-getCurrentIntervalLength(req));
	}

	private String getClanId(HttpServletRequest req){
		String clan = req.getParameter("pClanId");
		if (clan==null)
			clan="";
		return clan;
	}

	@Override
	protected NewsTotals getTotals(HttpServletRequest req, List<NewsEntry> entries) {
		return NewsTotalsUtility.calculateTotalsForClan(getClanId(req), entries);
	}
	
	
}
