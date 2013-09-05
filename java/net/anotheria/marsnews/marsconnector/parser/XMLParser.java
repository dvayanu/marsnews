package net.anotheria.marsnews.marsconnector.parser;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountryFactory;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.shared.AttackType;
import net.anotheria.marsnews.shared.AttackTypeHelper;
import net.anotheria.marsnews.shared.GovermentTypeHelper;
import net.anotheria.util.IOUtils;
import net.anotheria.util.StringUtils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class XMLParser {
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	
	public XMLParser() {
	}
	
	public static final String TO_REMOVE[] = new String[]{
		"xmlns=\"http://tempuri.org/\"",
	};
	
	public List<NewsEntry> parseNewsResponse(String content){
		try{
			SAXBuilder reader = new SAXBuilder();
			reader.setValidation(false);
			
			for (int i=0; i<TO_REMOVE.length; i++)
				content = StringUtils.removeString(content, TO_REMOVE[i]);
			

			Document doc = reader.build(new StringReader(content));

			Element root = doc.getRootElement();
			
			Element body = (Element)root.getChildren().get(0);
			
			Element myRoot = (Element)body.getChildren().get(0);
			
			return parseNews(myRoot);
			
			
			
		}catch(JDOMException e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	private List<NewsEntry> parseNews(Element root){
		root = root.getChild("ProvideNewsResult");
		root = root.getChild("searchdata");
		
		
		@SuppressWarnings("unchecked")List<Element> entries = root.getChildren("SearchNewsData");
		List<NewsEntry> ret = new ArrayList<NewsEntry>(entries.size());
		for (int i=0; i<entries.size(); i++){
			ret.add(parseNewsEntry(entries.get(i)));
		}
		return ret;
		
	}
	
	private NewsEntry parseNewsEntry(Element entry){
		NewsEntry e = null ;
		try{
			e = new NewsEntry();
			e.setId(Integer.parseInt(entry.getChildText("NewsId")));
			e.setAttackerId(Integer.parseInt(entry.getChildText("AttackerId")));
			e.setAttackerName(entry.getChildText("AttackerName"));
			e.setAttackerClan(entry.getChildText("AttackerClan"));
			
			e.setDefenderId(Integer.parseInt(entry.getChildText("DeffenderId")));
			e.setDefenderName(entry.getChildText("DeffenderName"));
			e.setDefenderClan(entry.getChildText("DeffenderClan"));
	
			e.setResult1(Integer.parseInt(entry.getChildText("Result1")));
			e.setResult2(Integer.parseInt(entry.getChildText("Result2")));
			
			e.setType(AttackTypeHelper.int2type(Integer.parseInt(entry.getChildText("Type"))));
			if (e.getType()==AttackType.UNKNOWN){
				System.out.println("Unknown type!: "+entry.getChildText("Type")+" in "+e);
			}
			if (entry.getChildText("IsLast").equals("true"))
				e.setKill(true);

			Date d = null;
			String dateString = entry.getChildText("When");
			//System.out.println("D:"+dateString.length()+" "+dateString);
			
			if (dateString.length()==19)
				dateString += ".000";
			if (dateString.length()==22)
				dateString += "0";
			//System.out.println("D:"+dateString.length()+" "+dateString);
			try{
				d = sdf1.parse(dateString);
				//System.out.println("First: "+d);
			}catch(ParseException ex){
				d = sdf2.parse(dateString);
				//System.out.println("Second: "+d);
			}
			//System.out.print(entry.getChildText("When")+" ");
			
			//System.out.println("-> "+d);
			e.setTimestamp(d.getTime());
			
			return e;
		}catch(Exception ex){
			//System.out.println(ex.getMessage()+" in "+e+" "+entry.getChildText("When"));
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String a[]) throws Exception{
		String content = IOUtils.readFileAtOnceAsString("test-scores.xml");
		System.out.println("Read: "+content.length()+" bytes");
		
		new XMLParser().parseRanksResponse(content);
		
	}
	
	public List<RankedCountry> parseRanksResponse(String content){
		try{
			SAXBuilder reader = new SAXBuilder();
			reader.setValidation(false);
			
			for (int i=0; i<TO_REMOVE.length; i++)
				content = StringUtils.removeString(content, TO_REMOVE[i]);
			

			Document doc = reader.build(new StringReader(content));

			Element root = doc.getRootElement();
			
			//List<Element> elems = root.getChildren();
			Element body = (Element)root.getChildren().get(0);
			
			Element myRoot = (Element)body.getChildren().get(0);
			Element result = myRoot.getChild("ProvideRankResult");
			
			
			
			return parseCountries(result);
			
			
		}catch(JDOMException e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<RankedCountry> parseCountries(Element root){
		
		List<Element> names = root.getChild("Country").getChildren();
		System.out.println(names.size());

		RankedCountry[] countries = new RankedCountry[names.size()];
		for (int i=0; i<countries.length; i++){
			countries[i] = RankedCountryFactory.createRankedCountry();
		}
			
		List<Element> countryIds = root.getChild("CountryId").getChildren();
		List<Element> ranks = root.getChild("Ranks").getChildren();
		List<Element> clans = root.getChild("Clan").getChildren();
		List<Element> land = root.getChild("Land").getChildren();
		List<Element> networth = root.getChild("Networth").getChildren();
		List<Element> special = root.getChild("Special").getChildren();
		List<Element> colors = root.getChild("Color").getChildren();
		

		int deadCountries = 0;
		int normalCountries = 0;
		int underProtection = 0;
		
		
		for (int i=0; i<names.size(); i++){
			countries[i].setCountryName(names.get(i).getText());
			countries[i].setClan(clans.get(i).getText());
			countries[i].setCountryId(Integer.parseInt(countryIds.get(i).getText()));
			countries[i].setRank(Integer.parseInt(ranks.get(i).getText()));
			countries[i].setLand(Integer.parseInt(land.get(i).getText()));
			countries[i].setNetworth(Integer.parseInt(networth.get(i).getText()));
			String sp = special.get(i).getText();
			countries[i].setGov(GovermentTypeHelper.char2int(sp.charAt(0)));
			if (sp.length()>1 && sp.charAt(1)=='G')
				countries[i].setGdi(true);
			countries[i].setColor(Integer.parseInt(colors.get(i).getText()));
			int col  = countries[i].getColor();
			if (col==4){
				countries[i].setProtection(true);
				underProtection++;
			}
			if (col==5){
				countries[i].setDead(true);
				deadCountries++;
			}
			if (col==0)
				normalCountries++;
			
		}
		
		System.out.println("Countries N: "+normalCountries+", D: "+deadCountries+", UP: "+underProtection);
		return Arrays.asList(countries);
	}
}
