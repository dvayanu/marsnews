package net.anotheria.marsnews.presentation.bean;

import java.util.ArrayList;
import java.util.List;

public class WarReportKillsBean extends BaseWarReportBean{
	private List<WarReportKillBean> kills;
	
	public WarReportKillsBean(){
		kills = new ArrayList<WarReportKillBean>();
	}

	public List<WarReportKillBean> getKills() {
		return kills;
	}

	public void setKills(List<WarReportKillBean> kills) {
		this.kills = kills;
	}
	
	public void addKill(WarReportKillBean kill){
		kills.add(kill);
	}
}
