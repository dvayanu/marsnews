package test;

import java.util.List;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.ranks.business.RankServiceFactory;
import net.anotheria.marsnews.ranks.business.RanksService;

public class TestScoreHistory {
	public static void main(String a[]){
		RanksService s = RankServiceFactory.createRanksService();
		List<RankedCountry> countries = s.getCountryHistory(115);
		System.out.println(countries.size());
		for (RankedCountry c : countries)
			System.out.println(c);
	}
}
