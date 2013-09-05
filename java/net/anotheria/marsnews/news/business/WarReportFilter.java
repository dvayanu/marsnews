package net.anotheria.marsnews.news.business;

import java.util.ArrayList;
import java.util.List;

import net.anotheria.util.NumberUtils;

public class WarReportFilter implements NewsFilter{

	private List<String> clans;
	private long timestampSince;
	private long timestampUntil;
	
	public WarReportFilter(){
		clans = new ArrayList<String>();
	}
	
	
	public boolean mayPass(NewsEntry e) {
		if (e.getTimestamp()<timestampSince)
			return false;
		if (e.getTimestamp()>timestampUntil)
			return false;
		
		String clan1 = e.getAttackerClan();
		String clan2 = e.getDefenderClan();
		
		if (clan1==null || clan2==null || clan1.length()==0 || clan2.length()==0)
			return false;
		return clans.indexOf(clan1)>-1 && clans.indexOf(clan2)>-1;
	}



	public long getTimestampSince() {
		return timestampSince;
	}


	public void setTimestampSince(long timestampSince) {
		this.timestampSince = timestampSince;
	}

	public String toString(){
		String ret = "";
		
		ret += "From "+NumberUtils.makeDigitalDateString(getTimestampSince())+" "+NumberUtils.makeTimeString(getTimestampSince());
		ret += " participating: "+clans;
		return ret;
	}
	
	public void addClan(String aClan){
		clans.add(aClan);
	}


	public long getTimestampUntil() {
		return timestampUntil;
	}


	public void setTimestampUntil(long timestampUntil) {
		this.timestampUntil = timestampUntil;
	}
}