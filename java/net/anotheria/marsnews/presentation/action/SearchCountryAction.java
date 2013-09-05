package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.presentation.bean.RankedCountryBean;

public class SearchCountryAction extends BaseNewsAction{

	@Override
	protected String getClanLinkTarget() {
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean af, HttpServletRequest req, HttpServletResponse res) throws Exception {

		String criteria = req.getParameter("pCriteria");
		if (criteria==null || criteria.length()==0){
			return mapping.findForward("success"); 
		}
		
		addBeanToSession(req, "lastSearchCriteria", criteria);
		
		List<RankedCountry> countries = new ArrayList<RankedCountry>();
		
		int countryId = -1;
		try{
			countryId = Integer.parseInt(criteria);
		}catch(NumberFormatException e){
			
		}
		
		if (countryId!=-1){
			RankedCountry c = getRanksService().getRankedCountry(countryId);
			if (c!=null)
				countries.add(c);
		}
		
		countries.addAll(getRanksService().getCountriesByName(criteria));
		
		if (countries.size()>0){
			ArrayList<RankedCountryBean> scoreBeans = new ArrayList<RankedCountryBean>(countries.size());
		
			for (RankedCountry c : countries)
				scoreBeans.add(createCountryRankBean(c));
		
			req.setAttribute("rankedCountries", scoreBeans);
			
			req.setAttribute("scores.ShowHistoryLink", Boolean.TRUE);
			req.setAttribute("scores.ShowNewsLink", Boolean.TRUE);
			
		}
		return mapping.findForward("success");
	}

}
