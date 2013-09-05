package net.anotheria.marsnews.stats;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.CountryManager;
import net.anotheria.util.sorter.DummySortType;
import net.anotheria.util.sorter.QuickSorter;
import net.anotheria.util.sorter.SortType;
import net.anotheria.util.sorter.Sorter;

public abstract class Stat {
	
	private StatValue overall;
	private Map<String, StatValue> clans;
	private Map<Integer, StatValue> countries;
	private Map<String, StatValue> clanCombinations;
	
	private Sorter sorter;
	private SortType st = new DummySortType();
	
	public Stat(){
		overall = new StatValue();
		clans = new HashMap<String, StatValue>();
		countries = new HashMap<Integer, StatValue>();
		clanCombinations = new HashMap<String, StatValue>();
		sorter = new QuickSorter();
	}
	
	public abstract void process(NewsEntry entry);
	
	public abstract String describe();
	
	protected boolean useForOverall(){ return true; }
	protected boolean useForClans(){ return true; }
	protected boolean useForCountries(){ return true; }
	protected boolean useForClanCombinations(){ return true; }
	
	public void increase(String clan, int countryId, String clanCombination){
		increase(clan, countryId, clanCombination, 1);
	}
	
	public void increase(String clan, int countryId, String clanCombination, long increment){
		if (useForOverall())
			overall.increase(increment);
		if (useForClans())
			getClanStatValue(clan).increase(increment);
		if (useForCountries())
			getCountryStatValue(countryId).increase(increment);
		if (useForClanCombinations())
			getClanCombinationStatValue(clanCombination).increase(increment);
		
	}
	
	

	private StatValue getClanCombinationStatValue(String combination){
		StatValue v = clanCombinations.get(combination);
		if (v==null){
			synchronized(clanCombinations){
				v = clanCombinations.get(combination);
				if (v==null){
					v = new StatValue();
					v.setName(combination);
					clanCombinations.put(combination, v);
				}
			}
		}
		return v;
	}

	private StatValue getClanStatValue(String clan){
		StatValue v = clans.get(clan);
		if (v==null){
			synchronized(clans){
				v = clans.get(clan);
				if (v==null){
					v = new StatValue();
					v.setName(clan);
					clans.put(clan, v);
				}
			}
		}
		return v;
	}
	
	private StatValue getCountryStatValue(int countryId){
		StatValue v = countries.get(countryId);
		if (v==null){
			synchronized(countries){
				v = countries.get(countryId);
				if (v==null){
					v = new StatValue();
					v.setName(CountryManager.getInstance().getCountry(countryId).toString());
					countries.put(countryId, v);
				}
			}
		}
		return v;
	}
	
	public void dumpClansHtml(PrintWriter out){
		out.println("<tr class=\"lineCaptions\"><th colspan=4 align=left>&nbsp;&nbsp;&nbsp;<b>"+describe()+"</b></th></tr>");
		out.println("<tr class=\"lineDark\">");
		out.println("<td width=1%>&nbsp;</td>");
		out.println("<td width=1%>All:</td>");
		out.println("<td>"+overall.getValue()+"</td>");
		out.println("<td>"+overall.getValue()/CountryManager.getInstance().getAmountOfCountries()+" per country</td>");
		out.println("</tr>");

		List<StatValue> stats = new ArrayList<StatValue>();
		stats.addAll(clans.values());
		stats = sorter.sort(stats, st);
		
		for (int i=0; i<stats.size(); i++){
			StatValue v = stats.get(i);
			String clazz = i % 2 == 1 ? "lineDark" : "lineLight";
			out.println("<tr class=\""+clazz+"\">");
			out.println("<td width=1%>"+(i+1)+"</td>");
			out.println("<td width=20%>"+v.getName()+"</td>");
			out.println("<td>"+v.getValue()+"</td>");
			double val = (double)v.getValue()/CountryManager.getInstance().getCountryCountInClan(v.getName());
			int intVal = (int)(val*100+5);
			val = (double)intVal/100;
			out.println("<td>"+val+"</td>");
			out.println("</tr>");
		}
	}


	public void dumpCountriesHtml(PrintWriter out){
		out.println("<tr class=\"lineCaptions\"><th colspan=3 align=left>&nbsp;&nbsp;&nbsp;<b>"+describe()+"</b></th></tr>");

		List<StatValue> stats = new ArrayList<StatValue>();
		stats.addAll(countries.values());
		stats = sorter.sort(stats, st);
		
		for (int i=0; i<stats.size(); i++){
			StatValue v = stats.get(i);
			String clazz = i % 2 == 1 ? "lineDark" : "lineLight";
			out.println("<tr class=\""+clazz+"\">");
			out.println("<td width=1%>"+(i+1)+"</td>");
			out.println("<td width=20%>"+v.getName()+"</td>");
			out.println("<td>"+v.getValue()+"</td>");
			out.println("</tr>");
		}
	}


	public void dumpClanCombinationHtml(PrintWriter out){
		out.println("<tr class=\"lineCaptions\"><th colspan=3 align=left>&nbsp;&nbsp;&nbsp;<b>"+describe()+"</b></th></tr>");
		out.println("<tr class=\"lineDark\">");
		out.println("<td width=1%>&nbsp;</td>");
		out.println("<td width=1%>Total:</td>");
		out.println("<td>"+overall.getValue()+"</td>");
		out.println("</tr>");

		List<StatValue> stats = new ArrayList<StatValue>();
		stats.addAll(clanCombinations.values());
		stats = sorter.sort(stats, st);
		
		for (int i=0; i<stats.size(); i++){
			StatValue v = stats.get(i);
			String clazz = i % 2 == 1 ? "lineDark" : "lineLight";
			out.println("<tr class=\""+clazz+"\">");
			out.println("<td width=1%>"+(i+1)+"</td>");
			out.println("<td width=20%>"+v.getName()+"</td>");
			out.println("<td>"+v.getValue()+"</td>");
			out.println("</tr>");
		}
	}

	public String dumpClans(){
		String ret = "";
		ret += "<b>"+describe()+"</b>\n";
		ret += "All: "+overall.getValue()+"\n";
		List<StatValue> stats = new ArrayList<StatValue>();
		stats.addAll(clans.values());
		stats = sorter.sort(stats, st);
		
		for (StatValue v : stats){
			ret += v.toString()+"\n";
		}
		
		return ret;
	}

	public String dumpCountries(){
		String ret = "";
		ret += "<b>"+describe()+"</b>\n";
		List<StatValue> stats = new ArrayList<StatValue>();
		stats.addAll(countries.values());
		stats = sorter.sort(stats, st);
		
		for (StatValue v : stats){
			ret += v.toString()+"\n";
		}
		
		return ret;
	}

}
