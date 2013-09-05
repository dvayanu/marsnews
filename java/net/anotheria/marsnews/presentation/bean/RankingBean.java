package net.anotheria.marsnews.presentation.bean;

import net.anotheria.marsnews.ranks.business.RankingVO;
import net.anotheria.util.NumberUtils;

public class RankingBean {
	private RankingVO vo;
	
	public RankingBean(RankingVO aVo){
		vo = aVo;
	}
	
	public String getNetworth(){
		return NumberUtils.getDotedNumber(vo.getNetworth());
	}
	
	public String getAverageNetworth(){
		return NumberUtils.getDotedNumber(vo.getAverageNetworth());
	}
	
	public String getLand(){
		return NumberUtils.getDotedNumber(vo.getLand());
	}
	
	public String getAverageLand(){
		return NumberUtils.getDotedNumber(vo.getAverageLand());
	}
	
	public String getTotalCountries(){
		return ""+vo.getNumberOfCountries();
	}
	
	public String getActiveCountries(){
		return ""+vo.getActiveCountries();
	}
	
	public String getNwLandRatio(){
		return NumberUtils.getDotedNumber(vo.getNetworthLandRatio());
	}
	
	public String getGdiCountries(){
		return ""+vo.getGdiCountries();
	}
	
	public String getCountriesUnderProtection(){
		return ""+vo.getCountriesUnderProtection();
	}
	
	public String getDeadCountries(){
		return ""+vo.getDeadCountries();
	}
	
	public String getTop10Countries(){
		return ""+vo.getCountriesInTop10();
	}

	public String getTop25Countries(){
		return ""+vo.getCountriesInTop25();
	}
	public String getTop50Countries(){
		return ""+vo.getCountriesInTop50();
	}
	public String getTop100Countries(){
		return ""+vo.getCountriesInTop100();
	}


}
