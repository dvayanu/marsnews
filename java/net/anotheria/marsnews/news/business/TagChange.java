package net.anotheria.marsnews.news.business;

import net.anotheria.util.Date;

public class TagChange {
	private String tag;
	private long timestamp;
	
	public TagChange(String aTag, long aTimestamp){
		tag = aTag;
		timestamp = aTimestamp;
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString(){
		return new Date(getTimestamp())+" -> "+getTag();
	}
}
