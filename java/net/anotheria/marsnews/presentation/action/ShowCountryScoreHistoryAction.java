package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountryVO;
import net.anotheria.marsnews.presentation.bean.RankedCountryBean;
import net.anotheria.util.NumberUtils;

public class ShowCountryScoreHistoryAction extends BaseNewsAction{

	@Override
	protected String getClanLinkTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean af, HttpServletRequest req, HttpServletResponse res) throws Exception {
		int countryId = -1;
		try{
			countryId = Integer.parseInt(req.getParameter("pCountryId"));
		}catch(Exception e){		}
		if (countryId==-1)
			throw new RuntimeException("No country selected");
		
		List<RankedCountry> countryHistory = getRanksService().getCountryHistory(countryId);
		List<RankedCountryBean> beans = new ArrayList<RankedCountryBean>();
		RankedCountry last = null;
		for (RankedCountry c : countryHistory){
			if (!isSame(last, c)){
				RankedCountryBean b = createCountryRankBean(c);
				long created = ((RankedCountryVO)c).getDaoCreated();
				b.setDate(NumberUtils.makeDigitalDateString(created)+" "+NumberUtils.makeTimeString(created));
				beans.add(b);
			}
			last = c;
			
		}
		if (beans.size()>0){
			Collections.reverse(beans);
			addBeanToRequest(req, "rankedCountries", beans);
			addBeanToRequest(req, "subtitle", countryHistory.get(0).getCountryName()+" ["+countryId+"]");
			addBeanToRequest(req, "scores.ShowDate", Boolean.TRUE);
		}
		
		return mapping.findForward("success");
	}
	
	private boolean isSame(RankedCountry c1, RankedCountry c2){
		return c1!=null && c2!=null &&
			   c1.getColor()==c2.getColor() &&
			   c1.getDead() == c2.getDead() && 
			   c1.getGdi() == c2.getGdi() &&
			   c1.getGov() == c2.getGov() &&
			   c1.getProtection() == c2.getProtection() &&
			   c1.getClan().equals(c2.getClan()) &&
			   c1.getLand() == c2.getLand() && 
			   c1.getNetworth() == c2.getNetworth();
	}

}
