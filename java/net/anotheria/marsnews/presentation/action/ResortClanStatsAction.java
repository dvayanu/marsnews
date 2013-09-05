package net.anotheria.marsnews.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.presentation.bean.ClanStatsCountryBeanSortType;
import net.anotheria.util.sorter.QuickSorter;
import net.anotheria.util.sorter.Sorter;
import net.anotheria.util.sorter.StaticQuickSorter;


public class ResortClanStatsAction extends BaseNewsAction{

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List beans = (List)req.getSession().getAttribute(ShowClanStatsAction.SA_CLAN_STATS_COUNTRIES);
		
		int sortBy = ClanStatsCountryBeanSortType.SORT_BY_DEFAULT;
		try{
			sortBy = Integer.parseInt(req.getParameter("pSortBy"));
		}catch(Exception ignored){}
		
		ClanStatsCountryBeanSortType sortType = new ClanStatsCountryBeanSortType(sortBy);
		req.getSession().setAttribute(ShowClanStatsAction.SA_CLAN_STATS_SORT_TYPE, sortType);
		beans = StaticQuickSorter.sort(beans, sortType);
		req.getSession().setAttribute(ShowClanStatsAction.SA_CLAN_STATS_COUNTRIES, beans);
		req.getSession().setAttribute(ShowClanStatsAction.SA_CLAN_STATS_SORT_TYPE, sortType);
		addBeanToRequest(req, "captions", ShowClanStatsAction.createCaptions(sortBy));
	
		return mapping.findForward("success");
	}
	
	
	
	public String getClanLinkTarget(){
		return "statsForClan";
	}

}
