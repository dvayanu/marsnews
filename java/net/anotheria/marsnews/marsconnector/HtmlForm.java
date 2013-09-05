package net.anotheria.marsnews.marsconnector;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HtmlForm implements Serializable{
	private String name;
	private String action;
	
	private Map<String,String> fields;
	
	public HtmlForm(String aName){
		this.name = aName;
		fields = new HashMap<String, String>();
	}
	
	public HtmlForm(){
		this("");
	}

	public void addField(String fieldName, String fieldValue){
		fields.put(fieldName, fieldValue);
	}

	public void setField(String fieldName, String fieldValue){
		if (!fields.containsKey(fieldName))
			throw new RuntimeException("No such field: "+fieldName);
		fields.put(fieldName, fieldValue);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "Form name:" +getName()+", action: "+getAction()+", Fields: "+fields;
	}
}
