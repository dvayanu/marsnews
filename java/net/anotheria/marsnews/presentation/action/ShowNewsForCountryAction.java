package net.anotheria.marsnews.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsServiceException;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.news.business.NewsTotalsUtility;
import net.anotheria.marsnews.presentation.bean.RankedCountryBean;
import net.anotheria.marsnews.shared.GovermentTypeHelper;

public class ShowNewsForCountryAction extends ShowNewsAction{

	@Override
	protected List<NewsEntry> getNewsEntriesSelection(HttpServletRequest req) throws NewsServiceException {
		String country = req.getParameter("pCountryId");
		if (country==null || country.length()==0)
			return getNewsService().getNews(System.currentTimeMillis()-getCurrentIntervalLength(req));
		int countryId = Integer.parseInt(country);
		
		prepareCountryRankBean(req, countryId);
		
		addBeanToRequest(req, "subtitle", "for country "+countryId);
		addBeanToRequest(req, "filterDefenderParam", "pDefId="+countryId);
		addBeanToRequest(req, "filterAttackerParam", "pAttId="+countryId);
		return getNewsService().getNewsForCountry(countryId, System.currentTimeMillis()-getCurrentIntervalLength(req));
	}
	
	private int getCountryId(HttpServletRequest req){
		return Integer.parseInt(req.getParameter("pCountryId"));
	}

	protected NewsTotals getTotals(HttpServletRequest req, List<NewsEntry> entries) {
		return NewsTotalsUtility.calculateTotalsForCountry(getCountryId(req), entries);
	}

	private void prepareCountryRankBean(HttpServletRequest req, int countryId){
		RankedCountry c = getRanksService().getRankedCountry(countryId);
		if (c==null){
			log.warn("Unknown country: "+c);
			return;
		}
		RankedCountryBean b = new RankedCountryBean();
		
		b.setRank(c.getRank());
		b.setLand(c.getLand());
		b.setNetworth(c.getNetworth());
		b.setName(c.getCountryName());
		b.setClan(c.getClan());
		b.setDead(c.getDead());
		b.setGdi(c.getGdi());
		b.setUnderProtection(c.getProtection());
		b.setGoverment(GovermentTypeHelper.int2string(c.getGov()));
		b.setCountryId(countryId);
		
		req.setAttribute("rankedCountry", b);
	}
}
