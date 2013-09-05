package net.anotheria.marsnews.news.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsTotalsUtility {
	public static final NewsTotals calculateTotalsForCountry(int countryId, List<NewsEntry> news){
		NewsTotals totals = new NewsTotals();

		for (NewsEntry e : news){
			if (e.getAttackerId()==countryId)
				totals.processAttackEntry(e);
			else
				totals.processDefenseEntry(e);
		}
		
		return totals;
	}
	
	public static final NewsTotals calculateTotalsForClan(String clanId, List<NewsEntry> news){
		NewsTotals totals = new NewsTotals();

		for (NewsEntry e : news){
			if (e.getAttackerClan().equals(clanId))
				totals.processAttackEntry(e);
			else
				totals.processDefenseEntry(e);
		}
		
		return totals;
	}
	
	public static final NewsTotals calculateTotals(List<NewsEntry> news){
		NewsTotals totals = new NewsTotals();

		for (NewsEntry e : news){
			totals.processAttackEntry(e);
			totals.processDefenseEntry(e);
		}
		
		return totals;
	}
	
	public static Map<String,NewsTotals> calculateTotalsForWarReport(List<NewsEntry> entries){
		HashMap<String, NewsTotals> container = new HashMap<String, NewsTotals>();
		
		
		for (NewsEntry e : entries){
			String code = e.getAttackClanCombination();
			NewsTotals totals = container.get(code);
			if (totals==null){
				totals = new NewsTotals();
				container.put(code, totals);
			}
			totals.processAttackEntry(e);
		}
		
		
		return container;
	}
}
 