package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.presentation.bean.RankingBean;
import net.anotheria.marsnews.presentation.bean.TopSelectionBean;
import net.anotheria.marsnews.presentation.bean.TopValueBean;
import net.anotheria.marsnews.ranks.business.RankingVO;
import net.anotheria.marsnews.ranks.business.RankingVOSortType;
import net.anotheria.util.sorter.QuickSorter;
import net.anotheria.util.sorter.Sorter;
import net.anotheria.util.sorter.StaticQuickSorter;

public class TopAction extends BaseNewsAction{

	@Override
	protected String getClanLinkTarget() {
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean af, HttpServletRequest req, HttpServletResponse res) throws Exception {

		int currentSelection = 0;
		try{
			currentSelection = Integer.parseInt(req.getParameter("selection"));
		}catch(Exception e){}
		if (currentSelection!=-1)
			addBeanToRequest(req, "currentSelection", currentSelection);
		
		ArrayList<TopSelectionBean> selection = new ArrayList<TopSelectionBean>();
		
		for (int i=0; i<RankingVOSortType.DESC.length; i++){
			TopSelectionBean b = new TopSelectionBean();
			b.setCaption(RankingVOSortType.DESC[i]);
			b.setSelection(i);
			selection.add(b);
		}
		addBeanToRequest(req, "selection", selection);
		addBeanToRequest(req, "currentValue", RankingVOSortType.DESC[currentSelection]);
		
		List<RankingVO> unsorted = getRanksService().getClanRankings();
		RankingVOSortType st = new RankingVOSortType(currentSelection);
		List<RankingVO> sorted = StaticQuickSorter.sort(unsorted, st);
		List<TopValueBean> beans = new ArrayList<TopValueBean>(sorted.size());
		int rank = 1;
		for (RankingVO vo : sorted){
			TopValueBean b = new TopValueBean();
			b.setRank(rank++);
			b.setClan(vo.getClan());
			b.setValue(vo.getSortingValue(currentSelection));
			beans.add(b);
		}
		addBeanToRequest(req, "top", beans);
		
		addBeanToRequest(req, "ranking", new RankingBean(getRanksService().getTotalRanking()));
		
		return mapping.findForward("success");
	}

}
