package net.anotheria.marsnews.presentation.bean;

public class IntervalSelectionBean {
	private String intervalName;
	private boolean selected;
	public String getIntervalName() {
		return intervalName;
	}
	public void setIntervalName(String intervalName) {
		this.intervalName = intervalName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public IntervalSelectionBean(String anIntervalName){
		intervalName = anIntervalName;
	}
}
