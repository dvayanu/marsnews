package net.anotheria.marsnews.presentation.bean;

public class TopValueBean {
	private int rank;
	private String clan;
	private String value;
	
	public TopValueBean(){
		
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
