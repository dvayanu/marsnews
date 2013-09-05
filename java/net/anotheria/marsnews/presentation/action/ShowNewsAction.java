package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.anoplass.api.util.paging.PagingControl;
import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsServiceException;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.presentation.bean.AttackTypeCounterBean;
import net.anotheria.marsnews.presentation.bean.NewsItemBean;
import net.anotheria.marsnews.presentation.bean.NewsTotalBean;
import net.anotheria.marsnews.shared.AttackTypeHelper;
import net.anotheria.util.slicer.Segment;
import net.anotheria.util.slicer.Slice;
import net.anotheria.util.slicer.Slicer;

public abstract class ShowNewsAction extends BaseNewsAction{
	
	
	@Override
	public ActionForward execute(ActionMapping mapping, FormBean form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<NewsEntry> entries = getNewsEntriesSelection(req);
		//System.out.println("Got "+entries.size()+" news entries for presentation.");
		
		List<NewsItemBean> beans = new ArrayList<NewsItemBean>(entries.size());
		for (NewsEntry entry : entries){
			beans.add(makeNewsItemBean(entry));
		}
		
		
		//prepare NewsTotals;
		NewsTotals totals = getTotals(req, entries);
		List<NewsTotalBean> gains = new ArrayList<NewsTotalBean>();
		addTotalIfNotZero(gains, totals.getLandGained(), "Land", D_LAND);
		addTotalIfNotZero(gains, totals.getBuildingsGained(), "Buildings", D_BUILDINGS);
		
		List<NewsTotalBean> kills = new ArrayList<NewsTotalBean>();
		addTotalIfNotZero(kills, totals.getKills(), "Kills", "");
		addTotalIfNotZero(kills, totals.getCiviliansKilled(), "Civilians", D_CIVILIANS);
		addTotalIfNotZero(kills, totals.getLandDestroyed(), "Land", D_LAND);
		addTotalIfNotZero(kills, totals.getBuildingsDestroyed(), "Buildings", D_BUILDINGS);
		addTotalIfNotZero(kills, totals.getUnitsKilled(), "Units", D_UNITS);
		addTotalIfNotZero(kills, totals.getFoodDestroyed(), "Food", D_FOOD);
		
		List<NewsTotalBean> loosage = new ArrayList<NewsTotalBean>();
		addTotalIfNotZero(loosage,  totals.getDeaths(), "Deaths", "");
		addTotalIfNotZero(loosage,  totals.getCiviliansLost(), "Civilians", D_CIVILIANS);
		addTotalIfNotZero(loosage,  totals.getLandLost(), "Land", D_LAND);
		addTotalIfNotZero(loosage,  totals.getBuildingsLost(), "Buildings", D_BUILDINGS);
		addTotalIfNotZero(loosage,  totals.getUnitsLost(), "Units", D_UNITS);
		addTotalIfNotZero(loosage,  totals.getFoodLost(), "Food", D_FOOD);

		if (kills.size()>0)
			req.setAttribute("totals.kills", kills);
		if (gains.size()>0)
			req.setAttribute("totals.gains", gains);
		if (loosage.size()>0)
			req.setAttribute("totals.loosage", loosage);
		
		//prepare attack and defense 
		List<AttackTypeCounterBean> attacks = new ArrayList<AttackTypeCounterBean>();
		List<AttackTypeCounterBean> defends = new ArrayList<AttackTypeCounterBean>();
		long count, held;
		for (int i=0; i<AttackTypeHelper.TYPES.length; i++){
			count = totals.getAttackCountForType(AttackTypeHelper.TYPES[i]);
			held  = totals.getAttackHeldCountForType(AttackTypeHelper.TYPES[i]);
			if (count>0){
				attacks.add(new AttackTypeCounterBean(AttackTypeHelper.TYPES[i], count, held));
			}

			count = totals.getDefenseCountForType(AttackTypeHelper.TYPES[i]);
			held  = totals.getDefenseHeldCountForType(AttackTypeHelper.TYPES[i]);
			if (count>0){
				defends.add(new AttackTypeCounterBean(AttackTypeHelper.TYPES[i], count, held));
			}
		}
		if (attacks.size()>0){
			long total=0;
			long totalHeld = 0;
			for (AttackTypeCounterBean b: attacks){
				total += b.getCount();
				totalHeld += b.getHeldCount();
			}
			
			req.setAttribute("totals.attacks", attacks);
			req.setAttribute("totals.attacks.total", new Long(total));
			req.setAttribute("totals.attacks.total.held", new Long(totalHeld));
			req.setAttribute("totals.attacks.total.broke", new Long(total-totalHeld));
		}
		if (defends.size()>0){
			long total=0;
			long totalHeld = 0;
			for (AttackTypeCounterBean b: defends){
				total += b.getCount();
				totalHeld += b.getHeldCount();
			}
			req.setAttribute("totals.defends", defends);
			req.setAttribute("totals.defends.total", new Long(total));
			req.setAttribute("totals.defends.total.held", new Long(totalHeld));
			req.setAttribute("totals.defends.total.broke", new Long(total-totalHeld));
		}

		//addBeanToSession(req, "-entries-", beans);
		int page = 1; 
		try{
			page = Integer.parseInt(req.getParameter("page"));
		}catch(NumberFormatException e){}
		
		Slice<NewsItemBean> slice = Slicer.slice(new Segment(page, 20), beans);
		addBeanToRequest(req, "entries", slice.getSliceData());
		PagingControl pc = new PagingControl(page, 20, slice.getTotalNumberOfItems());
		addBeanToRequest(req, "paging", pc);
		return mapping.findForward("success");
	}
	
	private void addTotalIfNotZero(List<NewsTotalBean> targetList, long value, String desc, String descriptor){
		if (value == 0)
			return;
		targetList.add(new NewsTotalBean(desc, value, descriptor));
	}

	protected abstract List<NewsEntry> getNewsEntriesSelection(HttpServletRequest req) throws NewsServiceException;
	
	protected abstract NewsTotals getTotals(HttpServletRequest req, List<NewsEntry> entries);
	
	
	public String getClanLinkTarget(){
		return "newsForClan";
	}
}
