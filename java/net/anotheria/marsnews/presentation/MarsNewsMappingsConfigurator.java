package net.anotheria.marsnews.presentation;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMappings;
import net.anotheria.maf.action.ActionMappingsConfigurator;


public class MarsNewsMappingsConfigurator implements ActionMappingsConfigurator{

	@Override
	public void configureActionMappings(ActionMappings actionMappings){
		actionMappings.addMapping("news", net.anotheria.marsnews.presentation.action.ShowAllNewsAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/News.jsp")
		);
		actionMappings.addMapping("page", net.anotheria.marsnews.presentation.action.PageNewsAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/News.jsp")
		);
		actionMappings.addMapping("newsForClan", net.anotheria.marsnews.presentation.action.ShowNewsForClanAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/News.jsp")
		);
		actionMappings.addMapping("newsForCountry", net.anotheria.marsnews.presentation.action.ShowNewsForCountryAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/News.jsp")
		);
		actionMappings.addMapping("filterNews", net.anotheria.marsnews.presentation.action.ShowNewsWithCombinedFilterAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/News.jsp")
		);

		actionMappings.addMapping("statsForClan", net.anotheria.marsnews.presentation.action.ShowClanStatsAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/ClanStats.jsp")
		);
		actionMappings.addMapping("resortStatsForClan", net.anotheria.marsnews.presentation.action.ResortClanStatsAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/ClanStats.jsp")
		);

		actionMappings.addMapping("warReport", net.anotheria.marsnews.presentation.action.ShowWarReportAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/WarReport.jsp"),
				new ActionForward("dialog", "/net/anotheria/marsnews/presentation/jsp/WarDialog.jsp")	
		);
		actionMappings.addMapping("scores", net.anotheria.marsnews.presentation.action.ShowScoresAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/Scores.jsp")
		);
		actionMappings.addMapping("scoreshistory", net.anotheria.marsnews.presentation.action.ShowCountryScoreHistoryAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/ScoresHistory.jsp")
		);
		actionMappings.addMapping("search", net.anotheria.marsnews.presentation.action.SearchCountryAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/SearchResult.jsp")
		);
		actionMappings.addMapping("top", net.anotheria.marsnews.presentation.action.TopAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/Top.jsp")
		);
		actionMappings.addMapping("xmlFeed", net.anotheria.marsnews.presentation.action.XMLFeedAction.class,
				new ActionForward("success", "/net/anotheria/marsnews/presentation/jsp/XMLFeed.jsp")
		);

		actionMappings.addMapping("forceScoresUpdate", net.anotheria.marsnews.presentation.action.ForceRanksUpdate.class);
		
	}

    	
}
