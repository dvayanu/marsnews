package net.anotheria.marsnews.presentation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.news.business.Country;
import net.anotheria.marsnews.news.business.CountryManager;
import net.anotheria.marsnews.news.business.NewsTotals;
import net.anotheria.marsnews.presentation.bean.AttackStatsTotalsBean;
import net.anotheria.marsnews.presentation.bean.CaptionBean;
import net.anotheria.marsnews.presentation.bean.ClanStatsCountryBean;
import net.anotheria.marsnews.presentation.bean.ClanStatsCountryBeanSortType;
import net.anotheria.marsnews.presentation.bean.RankedCountryBean;
import net.anotheria.marsnews.presentation.bean.RankingBean;
import net.anotheria.marsnews.ranks.business.RankingVO;
import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.util.sorter.StaticQuickSorter;


public class ShowClanStatsAction extends BaseNewsAction{

	public static final String SA_CLAN_STATS_SORT_TYPE = "clanStatsSortType";
	public static final String SA_CLAN_STATS_COUNTRIES = "clanStats.countries";
	public static final String SA_CLAN_STATS_TOTALS = "clanStats.totals";
	
	@Override
	public ActionForward execute(ActionMapping mapping, FormBean form, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String clanName = req.getParameter("pClanId");
		if (clanName==null || clanName.length()==0){
			addBeanToRequest(req, "captions", createCaptions(ClanStatsCountryBeanSortType.SORT_BY_DEFAULT));
			return mapping.findForward("success");
		}
		
		List<RankedCountry> countriesInScores = getRanksService().getCountriesInClan(clanName);
		ArrayList<RankedCountryBean> scoreBeans = new ArrayList<RankedCountryBean>(countriesInScores.size());
		
		for (RankedCountry c : countriesInScores)
			scoreBeans.add(createCountryRankBean(c));
		
		req.setAttribute("rankedCountries", scoreBeans);
		req.setAttribute("scores.ShowHistoryLink", Boolean.TRUE);
		req.setAttribute("scores.ShowNewsLink", Boolean.TRUE);

		req.setAttribute("subtitle", "for clan "+clanName);
		
		RankingVO ranking = getRanksService().getClanRanking(clanName);
		if (ranking!=null){
			req.setAttribute("ranking", new RankingBean(ranking));
		}
		
		
		List<Country> countries = CountryManager.getInstance().getCountriesInClan(clanName);
		//System.out.println("Got "+countries.size()+" for clan "+clanName);

		List<ClanStatsCountryBean> beans = new ArrayList<ClanStatsCountryBean>();
		AttackStatsTotalsBean total = new AttackStatsTotalsBean();
		for (int i=0; i<countries.size(); i++){
			Country c = countries.get(i);
			ClanStatsCountryBean bean = new ClanStatsCountryBean();
			NewsTotals totals = c.getTotals();
			
			bean.setId(c.getId());
			bean.setName(c.getName());
			bean.setDead(false); total.addDeads(0);
			bean.setKills(totals.getKills()); total.addKills(totals.getKills());
			
			bean.setAttackCountSS(totals.getAttackCountForType(AttackType.SS)); 
			bean.setAttackCountPS(totals.getAttackCountForType(AttackType.PS));
			bean.setAttackCountEM(totals.getAttackCountForType(AttackType.EM));
			bean.setAttackCountCM(totals.getAttackCountForType(AttackType.CM));
			bean.setAttackCountNM(totals.getAttackCountForType(AttackType.NM));
			bean.setAttackCountBR(totals.getAttackCountForType(AttackType.BR));
			bean.setAttackCountAB(totals.getAttackCountForType(AttackType.AB));
			bean.setAttackCountGS(totals.getAttackCountForType(AttackType.GS));
			
			bean.setDefendCountSS(totals.getDefenseCountForType(AttackType.SS));
			bean.setDefendCountPS(totals.getDefenseCountForType(AttackType.PS));
			bean.setDefendCountEM(totals.getDefenseCountForType(AttackType.EM));
			bean.setDefendCountCM(totals.getDefenseCountForType(AttackType.CM));
			bean.setDefendCountNM(totals.getDefenseCountForType(AttackType.NM));
			bean.setDefendCountBR(totals.getDefenseCountForType(AttackType.BR));
			bean.setDefendCountAB(totals.getDefenseCountForType(AttackType.AB));
			bean.setDefendCountGS(totals.getDefenseCountForType(AttackType.GS));
			
			beans.add(bean);
			
			total.addAttackCountSS(bean.getAttackCountSS());
			total.addAttackCountPS(bean.getAttackCountPS());
			total.addAttackCountGS(bean.getAttackCountGS());
			total.addAttackCountBR(bean.getAttackCountBR());
			total.addAttackCountAB(bean.getAttackCountAB());
			total.addAttackCountNM(bean.getAttackCountNM());
			total.addAttackCountCM(bean.getAttackCountCM());
			total.addAttackCountEM(bean.getAttackCountEM());

			total.addDefendCountSS(bean.getDefendCountSS());
			total.addDefendCountPS(bean.getDefendCountPS());
			total.addDefendCountGS(bean.getDefendCountGS());
			total.addDefendCountBR(bean.getDefendCountBR());
			total.addDefendCountAB(bean.getDefendCountAB());
			total.addDefendCountNM(bean.getDefendCountNM());
			total.addDefendCountCM(bean.getDefendCountCM());
			total.addDefendCountEM(bean.getDefendCountEM());
			
		}
		
		ClanStatsCountryBeanSortType sortType = (ClanStatsCountryBeanSortType)req.getSession().getAttribute(SA_CLAN_STATS_SORT_TYPE);
		if (sortType==null){
			sortType = new ClanStatsCountryBeanSortType();
			req.getSession().setAttribute(SA_CLAN_STATS_SORT_TYPE, sortType);
		}
			
		beans = StaticQuickSorter.sort(beans, sortType);
			 
		addBeanToSession(req, SA_CLAN_STATS_COUNTRIES, beans);
		addBeanToSession(req, SA_CLAN_STATS_TOTALS, total);
		addBeanToRequest(req, "captions", createCaptions(sortType.getSortBy()));
		return mapping.findForward("success");
	}
	
	static List<CaptionBean> createCaptions(int currentSortBy){
		List<CaptionBean> ret = new ArrayList<CaptionBean>();
		for (int i=0; i<ClanStatsCountryBeanSortType.SORT_BY_ALL.length; i++){
			CaptionBean bean = new CaptionBean();
			bean.setCaption(ClanStatsCountryBeanSortType.CAPTIONS[i]);
			bean.setLink("resortStatsForClan?pSortBy="+ClanStatsCountryBeanSortType.SORT_BY_ALL[i]);
			if (ClanStatsCountryBeanSortType.SORT_BY_ALL[i]==currentSortBy)
				bean.setSelected(true);
			ret.add(bean);
		}
		return ret;
	}
	
	public String getClanLinkTarget(){
		return "statsForClan";
	}


}
