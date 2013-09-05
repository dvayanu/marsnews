package net.anotheria.marsnews.shared;

public class Country {
	private int id;
	private String name;
	private String clan;
	
	
	
	private boolean alive;
	
	public String getClan() {
		return clan;
	}
	public void setClan(String clan) {
		this.clan = clan;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Country(){
		alive = true;
	}
	
	public Country(int anId, String aName, String aClan){
		id = anId;
		name = aName;
		clan = aClan;
		alive = true;
	}
	
	public String toString(){
		String ret = name +" ["+id+"]";
		if (clan!=null && clan.length()>0)
			ret += " ("+clan+")";
		return ret;

	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
