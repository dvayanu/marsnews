package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {
	public static void main(String a[]){
		List<String> str = new ArrayList<String>();
		
		str.add("a");
		str.add("d");
		str.add("b");
		str.add("c");
		Collections.sort(str);
		System.out.println(str);
	}
}
