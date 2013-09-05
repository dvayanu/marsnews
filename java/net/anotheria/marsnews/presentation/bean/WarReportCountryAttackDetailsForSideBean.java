package net.anotheria.marsnews.presentation.bean;

import java.util.ArrayList;
import java.util.List;

public class WarReportCountryAttackDetailsForSideBean extends BaseWarReportBean{
	private List<WarReportCountryAttackDetailsBean> countries;

	
	public WarReportCountryAttackDetailsForSideBean(){
		countries = new ArrayList<WarReportCountryAttackDetailsBean>();
	}
	
	public List<WarReportCountryAttackDetailsBean> getCountries() {
		return countries;
	}

	public void setCountries(List<WarReportCountryAttackDetailsBean> countries) {
		this.countries = countries;
	}
}
