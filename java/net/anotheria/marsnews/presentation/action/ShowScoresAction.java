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

public class ShowScoresAction extends BaseNewsAction{

	@Override
	protected String getClanLinkTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean af, HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<RankedCountry> countries = getRanksService().getRankedCountries();
		ArrayList<RankedCountryBean> beans = new ArrayList<RankedCountryBean>(countries.size());
		
		for (RankedCountry c : countries)
			beans.add(createCountryRankBean(c));
		
		req.setAttribute("rankedCountries", beans);
		
		req.setAttribute("scores.ShowHistoryLink", Boolean.TRUE);
		req.setAttribute("scores.ShowNewsLink", Boolean.TRUE);
		
		return mapping.findForward("success");
	}

}
