package net.anotheria.marsnews.presentation.bean;

public class NewsTotalBean {
	private String name;
	private long value;
	private String descriptor;
	
	public NewsTotalBean(String aName, long aValue, String aDescriptor){
		name = aName;
		value = aValue;
		descriptor = aDescriptor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	
	public String getDescriptor(){
		return descriptor;
	}
}
