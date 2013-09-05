package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.news.business.Country;
import net.anotheria.marsnews.news.business.CountryManager;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.news.business.NewsTotalsUtility;
import net.anotheria.marsnews.news.business.WarReportFilter;
import net.anotheria.marsnews.presentation.bean.WarReportAttackDetailBean;
import net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsBean;
import net.anotheria.marsnews.presentation.bean.WarReportCountryAttackDetailsForSideBean;
import net.anotheria.marsnews.presentation.bean.WarReportDestructionDetailBean;
import net.anotheria.marsnews.presentation.bean.WarReportKillBean;
import net.anotheria.marsnews.presentation.bean.WarReportKillsBean;
import net.anotheria.marsnews.presentation.bean.WarReportOverviewBean;
import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.util.Date;
import net.anotheria.util.NumberUtils;
import net.anotheria.webutils.bean.LabelValueBean;


public class ShowWarReportAction extends BaseNewsAction{
	
	public static final String PARAM_CLANS_SIDE_A = "pSideA";
	public static final String PARAM_CLANS_SIDE_B = "pSideB";

	public static final String PARAM_SINCE_DAY = "pSinceDay";
	public static final String PARAM_SINCE_MONTH = "pSinceMonth";
	public static final String PARAM_SINCE_HOUR  = "pSinceHour";
	public static final String PARAM_SINCE_MINUTE = "pSinceMinute";
	
	public static final String PARAM_UNTIL_DAY = "pUntilDay";
	public static final String PARAM_UNTIL_MONTH = "pUntilMonth";
	public static final String PARAM_UNTIL_HOUR  = "pUntilHour";
	public static final String PARAM_UNTIL_MINUTE = "pUntilMinute";
	
	private List<LabelValueBean> days, months, hours, minutes;
	
	public ShowWarReportAction(){
		prepareTimeSelectors();
	}
	

	@Override
	protected String getClanLinkTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean af, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		String queryParam = req.getParameter("query");
		if (queryParam==null || queryParam.length()==0){
			
			List<String> clans = getRanksService().getClanNames();
			List<List<String>> clanNameBeans = new ArrayList<List<String>>();
			List<String> current = new ArrayList();
			for (int i=0; i<clans.size(); i++){
				current.add(clans.get(i));
				if (current.size()==5){
					clanNameBeans.add(current);
					current = new ArrayList<String>();
				}
			}
			if (current.size()>0)
				clanNameBeans.add(current);
			
			req.setAttribute("clanNames", clanNameBeans);
			req.setAttribute("days", days);
			req.setAttribute("months", months);
			req.setAttribute("hours", hours);
			req.setAttribute("minutes", minutes);
			
			return mapping.findForward("dialog");
		}
		
		long timestampSince = 0;
		long timestampUntil = Long.MAX_VALUE;
		
		String pDaySince = req.getParameter(PARAM_SINCE_DAY);
		String pMonthSince = req.getParameter(PARAM_SINCE_MONTH);
		String pHourSince = req.getParameter(PARAM_SINCE_HOUR);
		String pMinuteSince = req.getParameter(PARAM_SINCE_MINUTE);
		
		String pDayUntil = req.getParameter(PARAM_UNTIL_DAY);
		String pMonthUntil = req.getParameter(PARAM_UNTIL_MONTH);
		String pHourUntil = req.getParameter(PARAM_UNTIL_HOUR);
		String pMinuteUntil = req.getParameter(PARAM_UNTIL_MINUTE);

		List<String> clansA = new ArrayList<String>();
		List<String> clansB = new ArrayList<String>();
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()){
			String pName = paramNames.nextElement();
			String pValue = req.getParameter(pName);
			if (pValue!=null && pValue.equals("sideA"))
				clansA.add(pName);
			if (pValue!=null && pValue.equals("sideB"))
				clansB.add(pName);
		}
		
		Date current = Date.currentDate();

		if (pDaySince!=null && pDaySince.length()>0){
			int day = Integer.parseInt(pDaySince);
			int month = pMonthSince==null || pMonthSince.length()==0? current.getMonth() : Integer.parseInt(pMonthSince);
			int hour = pHourSince==null || pHourSince.length()==0? 0 : Integer.parseInt(pHourSince);
			int minute = pMinuteSince==null || pMinuteSince.length()==0? 0 : Integer.parseInt(pMinuteSince);
			Date d = new Date(day, month, current.getYear(), hour, minute);
			timestampSince = d.toMill();
		}
		
		if (pDayUntil!=null && pDayUntil.length()>0){
			int day = Integer.parseInt(pDayUntil);
			int month = pMonthUntil==null || pMonthUntil.length()==0 ? current.getMonth() : Integer.parseInt(pMonthUntil);
			int hour = pHourUntil==null || pHourUntil.length()==0 ? 0 : Integer.parseInt(pHourUntil);
			int minute = pMinuteUntil==null || pMinuteUntil.length()==0 ? 0 : Integer.parseInt(pMinuteUntil);
			Date d = new Date(day, month, current.getYear(), hour, minute);
			timestampUntil = d.toMill();
		}

		WarReportFilter filter = new WarReportFilter();
		
		if (clansA.size()<1 || clansB.size()<1)
			throw new RuntimeException("You must specify at least 2 alliances, 1 on each side.");

		for (String c : clansA){
			filter.addClan(c);
		}
		
		for (String c : clansB){
			filter.addClan(c);
		}

		filter.setTimestampSince(timestampSince);
		filter.setTimestampUntil(timestampUntil);
		
		List<NewsEntry> entries = getNewsService().getFilteredNews(filter);
		
		List<NewsTotals> sideATotals, sideBTotals;
		sideATotals = new ArrayList<NewsTotals>();
		sideBTotals = new ArrayList<NewsTotals>();
		
		List<String> sideAKeys = new ArrayList<String>();
		List<String> sideBKeys = new ArrayList<String>();
		
		Map<String, NewsTotals> totalsMap = NewsTotalsUtility.calculateTotalsForWarReport(entries);
		Collection<String> keys = totalsMap.keySet();
		for (String k : keys){
			int indexOfSpace = k.indexOf(' ');
			String attackClan = k.substring(0, indexOfSpace).trim();
			if (clansA.indexOf(attackClan)!=-1){
				sideATotals.add(totalsMap.get(k));
				sideAKeys.add(k);
			}
			if (clansB.indexOf(attackClan)!=-1){
				sideBTotals.add(totalsMap.get(k));
				sideBKeys.add(k);
			}
		}
		
		
		NewsTotals sideACumulated = new NewsTotals();
		NewsTotals sideBCumulated = new NewsTotals();
		
		for (NewsTotals t : sideATotals)
			sideACumulated.addAll(t);
		
		for (NewsTotals t : sideBTotals)
			sideBCumulated.addAll(t);
		
		
		Map<String, List<NewsEntry>> killsBySideA = new HashMap<String, List<NewsEntry>>();
		Map<String, List<NewsEntry>> killsBySideB = new HashMap<String, List<NewsEntry>>();
		
		Map<String, Map<Integer, NewsTotals>> targetsBySideA = new HashMap<String, Map<Integer, NewsTotals>>();
		Map<String, Map<Integer, NewsTotals>> targetsBySideB = new HashMap<String, Map<Integer, NewsTotals>>();
		Map<String, Map<Integer, NewsTotals>> attackersBySideA = new HashMap<String, Map<Integer, NewsTotals>>();
		Map<String, Map<Integer, NewsTotals>> attackersBySideB = new HashMap<String, Map<Integer, NewsTotals>>();
		List<Integer> detectedKills = new ArrayList<Integer>();
		for (NewsEntry e : entries){
			if (e.isKill()){
				detectedKills.add(e.getDefenderId());
				Map<String,List<NewsEntry>> source = null;
				if (clansA.indexOf(e.getAttackerClan())!=-1)
					source = killsBySideA;
				if (clansB.indexOf(e.getAttackerClan())!=-1)
					source = killsBySideB;
				if (source==null){
					log.error("strange, can't find kill for entry: "+e);
				}
				List<NewsEntry> kills = source.get(e.getAttackClanCombination());
				if (kills==null){
					kills = new ArrayList<NewsEntry>();
					source.put(e.getAttackClanCombination(), kills);
				}
				kills.add(e);
			}
		}
		
		for (NewsEntry e: entries){
			if (!e.isKill() && detectedKills.indexOf(e.getDefenderId())==-1){
				Map<String, Map<Integer, NewsTotals>> source = null;
				if (clansA.indexOf(e.getAttackerClan())!=-1)
					source = targetsBySideA;
				if (clansB.indexOf(e.getAttackerClan())!=-1)
					source = targetsBySideB;
				if (source==null){
					log.error("strange, can't find attacker for entry: "+e);
					continue;
				}
				Map<Integer, NewsTotals> targets = source.get(e.getAttackClanCombination());
				if (targets==null){
					targets = new HashMap<Integer, NewsTotals>();
					source.put(e.getAttackClanCombination(), targets);
				}
				
				NewsTotals attacksOnTarget = targets.get(e.getDefenderId());
				if (attacksOnTarget==null){
					attacksOnTarget = new NewsTotals();
					targets.put(e.getDefenderId(), attacksOnTarget);
				}
				
				attacksOnTarget.processDefenseEntry(e);
			}
		}

		//calculate attackers
		for (NewsEntry e: entries){
			Map<String, Map<Integer, NewsTotals>> source = null;
			if (clansA.indexOf(e.getAttackerClan())!=-1)
				source = attackersBySideA;
			if (clansB.indexOf(e.getAttackerClan())!=-1)
				source = attackersBySideB;
			if (source==null){
				log.error("strange, can't find attacker for entry: "+e);
				continue;
			}
			Map<Integer, NewsTotals> attackers = source.get(e.getAttackClanCombination());
			if (attackers==null){
				attackers = new HashMap<Integer, NewsTotals>();
				source.put(e.getAttackClanCombination(), attackers);
			}
			
			NewsTotals attacksByAttacker = attackers.get(e.getAttackerId());
			if (attacksByAttacker==null){
				attacksByAttacker = new NewsTotals();
				attackers.put(e.getAttackerId(), attacksByAttacker);
			}
			
			attacksByAttacker.processAttackEntry(e);
		}
		
		
		req.setAttribute("sideA", clansA);
		req.setAttribute("overview.sideA", createOverviewBeans(sideATotals, sideAKeys));
		req.setAttribute("overview.sideA.cumulated", createOverviewBean(sideACumulated, ""));
		req.setAttribute("attacks.sideA", createAttackDetailBeans(sideATotals, sideAKeys));
		req.setAttribute("attacks.sideA.cumulated", createAttackDetailBean(sideACumulated, ""));
		req.setAttribute("destruction.sideA", createDestructionDetailBeans(sideATotals, sideAKeys));
		req.setAttribute("destruction.sideA.cumulated", createDestructionDetailBean(sideACumulated, ""));
		req.setAttribute("kills.sideA", createKillsBeans(killsBySideA));
		req.setAttribute("targets.sideA", createSideDefendDetailsBean(targetsBySideA));
		req.setAttribute("attackers.sideA", createSideAttackDetailsBean(attackersBySideA));
		
		req.setAttribute("sideB", clansB );
		req.setAttribute("overview.sideB", createOverviewBeans(sideBTotals, sideBKeys));
		req.setAttribute("overview.sideB.cumulated", createOverviewBean(sideBCumulated, ""));
		req.setAttribute("attacks.sideB", createAttackDetailBeans(sideBTotals, sideBKeys));
		req.setAttribute("attacks.sideB.cumulated", createAttackDetailBean(sideBCumulated, ""));
		req.setAttribute("destruction.sideB", createDestructionDetailBeans(sideBTotals, sideBKeys));
		req.setAttribute("destruction.sideB.cumulated", createDestructionDetailBean(sideBCumulated, ""));
		req.setAttribute("kills.sideB", createKillsBeans(killsBySideB));
		req.setAttribute("targets.sideB", createSideDefendDetailsBean(targetsBySideB));
		req.setAttribute("attackers.sideB", createSideAttackDetailsBean(attackersBySideB));

		
		return mapping.findForward("success");
	}
	
	private List<WarReportCountryAttackDetailsForSideBean> createSideDefendDetailsBean(Map<String, Map<Integer, NewsTotals>> targets){
		List<WarReportCountryAttackDetailsForSideBean> ret = new ArrayList<WarReportCountryAttackDetailsForSideBean>();

		for (String key : targets.keySet()){
			WarReportCountryAttackDetailsForSideBean b = new WarReportCountryAttackDetailsForSideBean();
			b.setKey(key);
			Map<Integer, NewsTotals> totalsMap = targets.get(key);
			b.setCountries(createCountryDefendDetailsBean(totalsMap));
			ret.add(b);
		}
		
		
		return ret;
	}
	
	private List<WarReportCountryAttackDetailsBean> createCountryDefendDetailsBean(Map<Integer, NewsTotals> targets){
		List<WarReportCountryAttackDetailsBean> ret = new ArrayList<WarReportCountryAttackDetailsBean>();
		
		for (Integer countryId : targets.keySet()){
			WarReportCountryAttackDetailsBean b = new WarReportCountryAttackDetailsBean();
			String cName = "Unknown #"+countryId;
			try{
				cName = CountryManager.getInstance().getCountry(countryId).getName();
				cName += " ["+countryId+"]";
			}catch(Exception ignored){}
			b.setCountryDescription(cName);
			NewsTotals totals = targets.get(countryId);

			String damage = "";
			if (totals.getLandLost()!=0)
				damage+= NumberUtils.getDotedNumber(totals.getLandLost())+"A ";
			
			if (totals.getBuildingsLost()!=0)
				damage += NumberUtils.getDotedNumber(totals.getBuildingsLost())+"B ";
			
			if (totals.getCiviliansLost()!=0)
				damage += NumberUtils.getDotedNumber(totals.getCiviliansLost())+"C ";
			
			if (totals.getUnitsLost()!=0)
				damage += NumberUtils.getDotedNumber(totals.getUnitsLost())+"U ";
			
			if (totals.getFoodLost()!=0){
				damage += NumberUtils.getDotedNumber(totals.getFoodLost())+"F";
			}
			
			if (damage.length()==0)
				damage = "no damage inflicted";
			b.setDamage(damage);

			
			b.setSs(totals.getDefenseCountForType(AttackType.SS));
			b.setSsBroke(b.getSs() - totals.getDefenseHeldCountForType(AttackType.SS));

			b.setPs(totals.getDefenseCountForType(AttackType.PS));
			b.setPsBroke(b.getPs() - totals.getDefenseHeldCountForType(AttackType.PS));

			b.setGs(totals.getDefenseCountForType(AttackType.GS));
			b.setGsBroke(b.getGs() - totals.getDefenseHeldCountForType(AttackType.GS));

			b.setBr(totals.getDefenseCountForType(AttackType.BR));
			b.setBrBroke(b.getBr() - totals.getAttackHeldCountForType(AttackType.BR));

			b.setAb(totals.getDefenseCountForType(AttackType.AB));
			b.setAbBroke(b.getAb() - totals.getDefenseHeldCountForType(AttackType.AB));

			b.setNm(totals.getDefenseCountForType(AttackType.NM));
			b.setNmBroke(b.getNm() - totals.getDefenseHeldCountForType(AttackType.NM));

			b.setCm(totals.getDefenseCountForType(AttackType.CM));
			b.setCmBroke(b.getCm() - totals.getDefenseHeldCountForType(AttackType.CM));

			b.setEm(totals.getDefenseCountForType(AttackType.EM));
			b.setEmBroke(b.getEm() - totals.getDefenseHeldCountForType(AttackType.EM));


			ret.add(b);
		}
		
		return ret;
	}
	
	private List<WarReportCountryAttackDetailsForSideBean> createSideAttackDetailsBean(Map<String, Map<Integer, NewsTotals>> targets){
		List<WarReportCountryAttackDetailsForSideBean> ret = new ArrayList<WarReportCountryAttackDetailsForSideBean>();

		for (String key : targets.keySet()){
			WarReportCountryAttackDetailsForSideBean b = new WarReportCountryAttackDetailsForSideBean();
			b.setKey(key);
			Map<Integer, NewsTotals> totalsMap = targets.get(key);
			b.setCountries(createCountryAttackDetailsBean(totalsMap));
			ret.add(b);
		}
		
		
		return ret;
	}
	
	private List<WarReportCountryAttackDetailsBean> createCountryAttackDetailsBean(Map<Integer, NewsTotals> targets){
		List<WarReportCountryAttackDetailsBean> ret = new ArrayList<WarReportCountryAttackDetailsBean>();
		
		for (Integer countryId : targets.keySet()){
			WarReportCountryAttackDetailsBean b = new WarReportCountryAttackDetailsBean();
			String cName = "Unknown #"+countryId;
			try{
				Country c = CountryManager.getInstance().getCountry(countryId); 
				cName = c.getName();
				cName += " ["+countryId+"]";
				if (c.isAlive()==false)
					b.setDead(true);
			}catch(Exception ignored){}
			b.setCountryDescription(cName);
			NewsTotals totals = targets.get(countryId);

			String damage = "";
			if ((totals.getLandDestroyed() + totals.getLandGained())!=0)
				damage+= NumberUtils.getDotedNumber((totals.getLandDestroyed() + totals.getLandGained()))+"A ";
			
			if ( (totals.getBuildingsDestroyed()+totals.getBuildingsGained())!=0)
				damage += NumberUtils.getDotedNumber(totals.getBuildingsDestroyed()+totals.getBuildingsGained())+"B ";
			
			if (totals.getCiviliansKilled()!=0)
				damage += NumberUtils.getDotedNumber(totals.getCiviliansKilled())+"C ";
			
			if (totals.getUnitsKilled()!=0)
				damage += NumberUtils.getDotedNumber(totals.getUnitsKilled())+"U ";
			
			if (totals.getFoodDestroyed()!=0){
				damage += NumberUtils.getDotedNumber(totals.getFoodDestroyed())+"F";
			}
			
			if (damage.length()==0)
				damage = "no damage inflicted";
			b.setDamage(damage);

			
			b.setSs(totals.getAttackCountForType(AttackType.SS));
			b.setSsBroke(b.getSs() - totals.getAttackHeldCountForType(AttackType.SS));

			b.setPs(totals.getAttackCountForType(AttackType.PS));
			b.setPsBroke(b.getPs() - totals.getAttackHeldCountForType(AttackType.PS));

			b.setGs(totals.getAttackCountForType(AttackType.GS));
			b.setGsBroke(b.getGs() - totals.getAttackHeldCountForType(AttackType.GS));

			b.setBr(totals.getAttackCountForType(AttackType.BR));
			b.setBrBroke(b.getBr() - totals.getAttackHeldCountForType(AttackType.BR));

			b.setAb(totals.getAttackCountForType(AttackType.AB));
			b.setAbBroke(b.getAb() - totals.getAttackHeldCountForType(AttackType.AB));

			b.setNm(totals.getAttackCountForType(AttackType.NM));
			b.setNmBroke(b.getNm() - totals.getAttackHeldCountForType(AttackType.NM));

			b.setCm(totals.getAttackCountForType(AttackType.CM));
			b.setCmBroke(b.getCm() - totals.getAttackHeldCountForType(AttackType.CM));

			b.setEm(totals.getAttackCountForType(AttackType.EM));
			b.setEmBroke(b.getEm() - totals.getAttackHeldCountForType(AttackType.EM));

			b.setKills(totals.getKills());
			
			ret.add(b);
		}
		
		return ret;
	}

	private List<WarReportKillsBean> createKillsBeans(Map<String, List<NewsEntry>> kills){
		List<WarReportKillsBean> ret = new ArrayList<WarReportKillsBean>();
		
		for (String key : kills.keySet()){
			WarReportKillsBean b = new WarReportKillsBean();
			b.setKey(key);
			List<NewsEntry> killNews = kills.get(key);
			for (NewsEntry e : killNews){
				b.addKill(createKillBean(e));
			}
			ret.add(b);
		}
		
		return ret;
	}
	
	private WarReportKillBean createKillBean(NewsEntry e){
		WarReportKillBean b = new WarReportKillBean();
		
		b.setKillerCountry(describeCountry(e.getAttackerId(), e.getAttackerName()));
		b.setKilledCountry(describeCountry(e.getDefenderId(), e.getDefenderName()));
		b.setKilledBy(""+e.getType());
		
		return b;
	}
	
	private String describeCountry(int countryId, String countryName){
		return countryName+" ["+countryId+"]";		
	}
	
	private List<WarReportOverviewBean> createOverviewBeans(List<NewsTotals> totals, List<String> keys){
		List<WarReportOverviewBean> ret = new ArrayList<WarReportOverviewBean>();
		for (int i= 0; i<totals.size(); i++)
			ret.add(createOverviewBean(totals.get(i), keys.get(i)));
		return ret;
	}
	
	private List<WarReportAttackDetailBean> createAttackDetailBeans(List<NewsTotals> totals, List<String> keys){
		List<WarReportAttackDetailBean> ret = new ArrayList<WarReportAttackDetailBean>();
		for (int i= 0; i<totals.size(); i++)
			ret.add(createAttackDetailBean(totals.get(i), keys.get(i)));
		return ret;
	}

	private List<WarReportDestructionDetailBean> createDestructionDetailBeans(List<NewsTotals> totals, List<String> keys){
		List<WarReportDestructionDetailBean> ret = new ArrayList<WarReportDestructionDetailBean>();
		for (int i= 0; i<totals.size(); i++)
			ret.add(createDestructionDetailBean(totals.get(i), keys.get(i)));
		return ret;
	}

	private WarReportOverviewBean createOverviewBean(NewsTotals totals, String key){
		WarReportOverviewBean b = new WarReportOverviewBean();
		
		b.setAttackers(totals.getAttackerCount());
		b.setTargets(totals.getAttackedCount());
		b.setTotalHits(totals.getTotalAttacks(false));
		b.setSuccessfulHits(totals.getTotalAttacks(true));
		b.setKills(totals.getKills());
		b.setKey(key);
		
		return b;
	}
	
	private WarReportAttackDetailBean createAttackDetailBean(NewsTotals totals, String key){
		WarReportAttackDetailBean bean = new WarReportAttackDetailBean();
		bean.setKey(key);
		bean.setSs(totals.getAttackCountForType(AttackType.SS));
		bean.setPs(totals.getAttackCountForType(AttackType.PS));
		bean.setGs(totals.getAttackCountForType(AttackType.GS));
		bean.setBr(totals.getAttackCountForType(AttackType.BR));
		bean.setAb(totals.getAttackCountForType(AttackType.AB));
		bean.setNm(totals.getAttackCountForType(AttackType.NM));
		bean.setCm(totals.getAttackCountForType(AttackType.CM));
		bean.setEm(totals.getAttackCountForType(AttackType.EM));
		
		return bean;
	}
	
	private WarReportDestructionDetailBean createDestructionDetailBean(NewsTotals totals, String key){
		WarReportDestructionDetailBean bean = new WarReportDestructionDetailBean();
		
		bean.setKey(key);
		
		bean.setLandDestroyed(totals.getLandDestroyed());
		bean.setLandTaken(totals.getLandGained());
		
		bean.setBuildingsDestroyed(totals.getBuildingsDestroyed());
		bean.setBuildingsTaken(totals.getBuildingsGained());
		
		bean.setCiviliansDestroyed(totals.getCiviliansKilled());
		bean.setFoodDestroyed(totals.getFoodDestroyed());
		
		return bean;
	}
	
	private void prepareTimeSelectors(){
		LabelValueBean first = new LabelValueBean("","");

		days = new ArrayList<LabelValueBean>();
		days.add(first);
		for (int i=1; i<=31; i++){
			days.add(new LabelValueBean(""+i, itoa(i)));
		}
		
		months = new ArrayList<LabelValueBean>();
		months.add(first);
		for (int i=1; i<=12; i++){
			months.add(new LabelValueBean(""+i, itoa(i)));
		}
		
		hours = new ArrayList<LabelValueBean>();
		hours.add(first);
		for (int i=0; i<=23; i++){
			hours.add(new LabelValueBean(""+i, itoa(i)));
		}

		minutes = new ArrayList<LabelValueBean>();
		minutes.add(first);
		for (int i=0; i<=59; i++){
			minutes.add(new LabelValueBean(""+i, itoa(i)));
		}
	}
	
}
