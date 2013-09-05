package test.service;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import net.anotheria.marsnews.news.business.Country;
import net.anotheria.marsnews.news.business.CountryManager;

public class PrintCountries {
	public static void main(String a[]){
		BasicConfigurator.configure();
		List<Country> countries = CountryManager.getInstance().getCountries();
		System.out.println("Got "+countries.size()+" countries.");
		for (int i=0; i<countries.size(); i++){
			if (countries.get(i).getTagChanges().size()>1){
				String s = countries.get(i).toString();
				if (s.indexOf("WDU")!=-1)
					System.out.println(s);
			}
			
		}
	}
}
