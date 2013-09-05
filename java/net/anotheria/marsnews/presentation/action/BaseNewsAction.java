package net.anotheria.marsnews.presentation.action;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.news.business.CombiNewsFilter;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsService;
import net.anotheria.marsnews.news.business.NewsServiceFactory;
import net.anotheria.marsnews.presentation.bean.IntervalSelectionBean;
import net.anotheria.marsnews.presentation.bean.NewsItemBean;
import net.anotheria.marsnews.presentation.bean.RankedCountryBean;
import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.marsnews.shared.GovermentTypeHelper;
import net.anotheria.marsnews.shared.Intervals;
import net.anotheria.util.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseNewsAction extends BaseMarsNewsAction{

	public static final String PARAM_ATTACKER_ID = "pAttId";
	public static final String PARAM_DEFENDER_ID = "pDefId";
	public static final String PARAM_ATTACKER_CLAN = "pAttClan";
	public static final String PARAM_DEFENDER_CLAN = "pDefClan";
	public static final String PARAM_TYPE = "pType";
	public static final String PARAM_HELD_ONLY = "pHeldOnly";
	public static final String PARAM_BROKE_ONLY = "pBrokeOnly";
	public static final String PARAM_KILL_ONLY  = "pKillOnly";

	public static final String D_LAND = "H";
	public static final String D_BUILDINGS = "B";
	public static final String D_CIVILIANS = "C";
	public static final String D_FOOD = "F";
	public static final String D_UNITS = "U";


	public static final String SA_CURRENT_INTERVAL = "currentInterval";
	
	private NewsService newsService;
	
	protected BaseNewsAction(){
		newsService = (NewsService)createServiceMonitoringInstance(NewsService.class, NewsServiceFactory.getInstance());
	}
	
	public long getCurrentIntervalLength(HttpServletRequest req){
		return Intervals.getIntervalDuration(getCurrentInterval(req))+Intervals.HOUR*2;
	}
	
	protected String getCurrentInterval(HttpServletRequest req){
		String currentInterval = (String)req.getSession().getAttribute(SA_CURRENT_INTERVAL);
		if (currentInterval==null)
			currentInterval = Intervals.getDefaultInterval();
		return currentInterval;
	}
	
	protected NewsService getNewsService(){
		return newsService;
	}
	
	protected abstract String getClanLinkTarget();
	
	protected CombiNewsFilter createFilterFromRequest(HttpServletRequest req){
		CombiNewsFilter filter = new CombiNewsFilter();
		
		Map<String,String[]> params = req.getParameterMap();
		
		filter.setTimestampSince(System.currentTimeMillis()-getCurrentIntervalLength(req));
		
		if (params.containsKey(PARAM_ATTACKER_ID)){
			try{
				filter.setAttackerId(Integer.parseInt(params.get(PARAM_ATTACKER_ID)[0]));
			}catch(NumberFormatException ignored){}
		}
		
		if (params.containsKey(PARAM_DEFENDER_ID)){
			try{
				filter.setDefenderId(Integer.parseInt(params.get(PARAM_DEFENDER_ID)[0]));
			}catch(NumberFormatException ignored){}
		}
		
		if (params.containsKey(PARAM_ATTACKER_CLAN)){
			filter.setAttackerClan(params.get(PARAM_ATTACKER_CLAN)[0]);
		}
		
		if (params.containsKey(PARAM_DEFENDER_CLAN)){
			filter.setDefenderClan(params.get(PARAM_DEFENDER_CLAN)[0]);
		}
		
		if (params.containsKey(PARAM_TYPE))
			filter.setType(AttackType.valueOf(params.get(PARAM_TYPE)[0]));
		
		filter.setKillOnly(isFlagSet(params.get(PARAM_KILL_ONLY)));
		filter.setHeldOnly(isFlagSet(params.get(PARAM_HELD_ONLY)));
		filter.setBrokeOnly(isFlagSet(params.get(PARAM_BROKE_ONLY)));
		return filter;
	}
	
	private boolean isFlagSet(String[] s){
		return s!=null && s[0].equals("true");
	}

	protected NewsItemBean makeNewsItemBean(NewsEntry e){
		NewsItemBean ret = new NewsItemBean();
		
		ret.setAttacker(e.getAttackerName()+" (#"+e.getAttackerId()+")");
		ret.setDefender(e.getDefenderName()+" (#"+e.getDefenderId()+")");
		ret.setAttackerId(e.getAttackerId());
		ret.setDefenderId(e.getDefenderId());
		ret.setAttackerClan(e.getAttackerClan());
		ret.setDefenderClan(e.getDefenderClan());
		
		ret.setDate(NumberUtils.makeDigitalDateString(e.getTimestamp())+" "+NumberUtils.makeTimeString(e.getTimestamp()));
		ret.setType(e.getType().toString());
		
		String result = ""+e.getResult1();
		result += getPrimaryAttackCharacterDescriptor(e);
		if (e.getType()!=AttackType.NM && e.getType()!=AttackType.EM){
			result += " / "+e.getResult2();
			result += getSecondaryAttackCharacterDescriptor(e);
		}
		ret.setResult(result);
		
		if (e.isHeld())
			ret.setHeld(true);
		if (e.isKill())
			ret.setKill(true);
		
		return ret;
	}
	
	protected String getPrimaryAttackCharacterDescriptor(NewsEntry e){
		switch(e.getType()){
		case SS:
		case PS:
		case NM:
			return D_LAND;
		case AB:
			return D_CIVILIANS;
		case EM:
			return D_UNITS;
		case GS:
		case BR:
		case CM:
			return D_CIVILIANS;
		}
		return "?";
		
	}
	protected String getSecondaryAttackCharacterDescriptor(NewsEntry e){
		switch(e.getType()){
		case SS:
		case PS:
		case BR:
		case CM:
			return D_BUILDINGS;
		case AB:
			return D_BUILDINGS;
		case EM:
		case NM:
			return "?";
		case GS:
			return D_FOOD;
		}
		return "?";
	}

	protected RankedCountryBean createCountryRankBean(RankedCountry c){
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
		b.setCountryId(c.getCountryId());
		
		return b;
	}

	@Override
	public void postProcess(net.anotheria.maf.action.ActionMapping arg0,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		try{
			req.setAttribute("lastNewsEntry", getNewsService().getNewestNewsEntry());
		}catch(Exception ignored){
			req.setAttribute("lastNewsEntry", "No News Yet");
		}
		addBeanToRequest(req, "genTimestamp", NumberUtils.makeISO8601TimestampString(System.currentTimeMillis()));
	}

	@Override
	public void preProcess(net.anotheria.maf.action.ActionMapping arg0,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String linkToCurrentPage = "";
		String reqPathInfo = req.getPathInfo();
		if (reqPathInfo!=null && reqPathInfo.length()>1)
		linkToCurrentPage += reqPathInfo.substring(1);
		String qs = req.getQueryString();
		if (qs!=null)
			linkToCurrentPage+="?"+qs;
		else
			linkToCurrentPage += "?dummy=d";
		
		int indexOfIntervalParamer = linkToCurrentPage.indexOf("pInterval");
		if (indexOfIntervalParamer!=-1)
			linkToCurrentPage = linkToCurrentPage.substring(0, indexOfIntervalParamer-1);
		
		int indexOfPageParameter = linkToCurrentPage.indexOf("page");
		if (indexOfPageParameter!=-1)
			linkToCurrentPage = linkToCurrentPage.substring(0, indexOfPageParameter-1);
		
		addBeanToRequest(req, "linkToCurrentPage", linkToCurrentPage);
		
		String selectedInterval = req.getParameter("pInterval");
		if (selectedInterval!=null && selectedInterval.length()>0)
			req.getSession().setAttribute(SA_CURRENT_INTERVAL, selectedInterval);
		
		//prepare intervals;
		String currentInterval = getCurrentInterval(req);
		List<IntervalSelectionBean> intervals = new ArrayList<IntervalSelectionBean>(Intervals.INTERVAL_DESCS.length);
		for (int i=0; i<Intervals.INTERVAL_DESCS.length; i++){
			IntervalSelectionBean intBean = new IntervalSelectionBean(Intervals.INTERVAL_DESCS[i]);
			if (intBean.getIntervalName().equals(currentInterval))
				intBean.setSelected(true);
			intervals.add(intBean);
		}
		addBeanToRequest(req, "intervals", intervals);
		
		//prepare clans
		addBeanToRequest(req, "clans", getRanksService().getClanNames());
		//System.out.println("CLans: "+CountryManager.getInstance().getClansNames());
		
		addBeanToRequest(req, "clanLink", getClanLinkTarget());
	
	}
	
	protected String itoa(int i){
		return NumberUtils.itoa(i);
	}

}
