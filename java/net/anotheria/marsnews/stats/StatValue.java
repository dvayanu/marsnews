package net.anotheria.marsnews.stats;

import net.anotheria.util.BasicComparable;
import net.anotheria.util.sorter.IComparable;

public class StatValue implements IComparable{
	private long value;
	private String name;
	
	public int compareTo(IComparable anotherComparable, int method) {
		return BasicComparable.compareLong(((StatValue)anotherComparable).getValue(), getValue());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatValue(){
		
	}
	
	public StatValue(long aValue){
		value = aValue;
	}
	
	public void increase(){
		value++;
	}
	
	public void increase(long increment){
		value += increment;
	}

	public void decrease(){
		value--;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	
	}
	
	public String toString(){
		return name+": "+getValue();
	}
}
