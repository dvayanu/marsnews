package net.anotheria.marsnews.stats;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.CountryManager;

public class StatsContainer {
	private List<Stat> stats;
	
	public StatsContainer(){
		stats = new ArrayList<Stat>();
	}
	
	public void process(NewsEntry e){
		for (Stat s : stats)
			s.process(e);
	}
	
	public void dumpClansHtml(PrintWriter out){
		out.println("<table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"3\">");
		
		out.println("<tr class=\"lineCaptions\"><th colspan=\"4\" align=left>&nbsp;&nbsp;<b>Countries</b> (Alive)</th></tr>");
		List<String> clans = CountryManager.getInstance().getClans();

		out.println("<tr class=\"lineDark\">");
		out.println("<td>&nbsp;</td>");
		out.println("<td>All</td>");
		out.println("<td>"+CountryManager.getInstance().getAmountOfCountries()+"</td>");
		out.println("<td>&nbsp;</td>");
		out.println("</tr>");

		for (int i=0; i<clans.size(); i++){
			String clan = clans.get(i);
			String clazz = i % 2 == 0 ? "lineLight" : "lineDark";
			out.println("<tr class=\""+clazz+"\">");
			out.println("<td>&nbsp;</td>");
			out.println("<td>"+clan+"</td>");
			out.println("<td>"+CountryManager.getInstance().getCountryCountInClan(clan)+" ("+CountryManager.getInstance().getAliveCountryCountInClan(clan)+")"+"</td>");
			out.println("<td>&nbsp;</td>");
			out.println("</tr>");
		}

		
		for (Stat s : stats){
			s.dumpClansHtml(out);
		}
		
		out.println("</table>");
	}
	
	public void dumpCountriesHtml(PrintWriter out){
		out.println("<table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"3\">");
		for (Stat s : stats){
			s.dumpCountriesHtml(out);
		}
		
		out.println("</table>");
	}

	public void dumpClanCombinationsHtml(PrintWriter out){
		out.println("<table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"3\">");
		for (Stat s : stats){
			s.dumpClanCombinationHtml(out);
		}
		
		out.println("</table>");
	}

	public void dumpClans(){
		for (Stat s : stats){
			System.out.println(s.dumpClans());
			System.out.println("=================================");
		}
	}
	
	public void dumpCountries(){
		for (Stat s : stats){
			System.out.println(s.dumpCountries());
			System.out.println("=================================");
		}
	}

	public void addStat(Stat s){
		stats.add(s);
	}
}
