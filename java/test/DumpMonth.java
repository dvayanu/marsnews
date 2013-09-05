package test;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.news.persistence.NewsPersistenceService;
import net.anotheria.marsnews.news.persistence.NewsPersistenceServiceJDBCFactory;
import net.anotheria.marsnews.shared.CountryManager;
import net.anotheria.marsnews.stats.ABAttackCounter;
import net.anotheria.marsnews.stats.ABDefenseCounter;
import net.anotheria.marsnews.stats.AttackCounter;
import net.anotheria.marsnews.stats.BRAttackCounter;
import net.anotheria.marsnews.stats.BRDefenseCounter;
import net.anotheria.marsnews.stats.BuildingsDestroyedCounter;
import net.anotheria.marsnews.stats.BuildingsLostCounter;
import net.anotheria.marsnews.stats.CMAttackCounter;
import net.anotheria.marsnews.stats.CMDefenseCounter;
import net.anotheria.marsnews.stats.CiviliansKilledCounter;
import net.anotheria.marsnews.stats.CiviliansLostCounter;
import net.anotheria.marsnews.stats.DefenseCounter;
import net.anotheria.marsnews.stats.EMAttackCounter;
import net.anotheria.marsnews.stats.EMDefenseCounter;
import net.anotheria.marsnews.stats.GSAttackCounter;
import net.anotheria.marsnews.stats.GSDefenseCounter;
import net.anotheria.marsnews.stats.GainedLandCounter;
import net.anotheria.marsnews.stats.KillCounter;
import net.anotheria.marsnews.stats.LandDestroyedCounter;
import net.anotheria.marsnews.stats.NMAttackCounter;
import net.anotheria.marsnews.stats.NMDefenseCounter;
import net.anotheria.marsnews.stats.PSAttackCounter;
import net.anotheria.marsnews.stats.PSDefenseCounter;
import net.anotheria.marsnews.stats.SSAttackCounter;
import net.anotheria.marsnews.stats.SSDefenseCounter;
import net.anotheria.marsnews.stats.StatsContainer;
import net.anotheria.util.Date;

public class DumpMonth {
	
	private static StatsContainer setupContainer(){
		StatsContainer c = new StatsContainer();
		c.addStat(new AttackCounter());
		c.addStat(new DefenseCounter());
		c.addStat(new KillCounter());

		c.addStat(new SSAttackCounter());
		c.addStat(new PSAttackCounter());
		c.addStat(new GSAttackCounter());
		c.addStat(new BRAttackCounter());
		c.addStat(new ABAttackCounter());
		c.addStat(new NMAttackCounter());
		c.addStat(new CMAttackCounter());
		c.addStat(new EMAttackCounter());

		c.addStat(new SSDefenseCounter());
		c.addStat(new PSDefenseCounter());
		c.addStat(new GSDefenseCounter());
		c.addStat(new BRDefenseCounter());
		c.addStat(new ABDefenseCounter());
		c.addStat(new NMDefenseCounter());
		c.addStat(new CMDefenseCounter());
		c.addStat(new EMDefenseCounter());

		
		c.addStat(new GainedLandCounter());
		c.addStat(new BuildingsLostCounter());
		c.addStat(new CiviliansKilledCounter());
		c.addStat(new CiviliansLostCounter());
		c.addStat(new LandDestroyedCounter());
		c.addStat(new BuildingsDestroyedCounter());
		
		
		
		return c;
	}
	
	public static void main(String a[]) throws Exception{
		
		StatsContainer container = setupContainer();
		CountryManager manager = CountryManager.getInstance();

		NewsPersistenceService newsService = new NewsPersistenceServiceJDBCFactory().getService();
		
		long duration = 0;
		List<NewsEntry> entries = newsService.getAllNewsEntries();
		long start = System.currentTimeMillis();
		for (NewsEntry e : entries){
			manager.process(e);
			container.process(e);
		}
		long end = System.currentTimeMillis();
		duration = end - start;

		System.out.println("Done "+entries.size()+" in "+duration+" ms.");
		System.out.println("Countries found: "+CountryManager.getInstance().getAmountOfCountries());
		
		FileOutputStream fOut3 = new FileOutputStream("wars.html");
		PrintWriter pOut3 = new PrintWriter(fOut3);
		pOut3.println("<html><head><title>Wars - 01 April 07 - "+new Date(System.currentTimeMillis()).toString()+"</title>");
		pOut3.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"temp.css\" />");
		pOut3.println("</head><body>");
		container.dumpClanCombinationsHtml(pOut3);
		pOut3.println("</body></html>");
		pOut3.close();
		fOut3.close();

		FileOutputStream fOut = new FileOutputStream("clans.html");
		PrintWriter pOut = new PrintWriter(fOut);
		pOut.println("<html><head><title>Clans - 01 April 07 - 26 Mai 2007</title>");
		pOut.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"temp.css\" />");
		pOut.println("</head><body>");
		container.dumpClansHtml(pOut);
		pOut.println("</body></html>");
		pOut.flush();
		pOut.close();

		FileOutputStream fOut2 = new FileOutputStream("countries.html");
		PrintWriter pOut2 = new PrintWriter(fOut2);
		pOut2.println("<html><head><title>Countries - 01 April 07 - 26 Mai 2007</title>");
		pOut2.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"temp.css\" />");
		pOut2.println("</head><body>");
		container.dumpCountriesHtml(pOut2);
		pOut2.println("</body></html>");
		pOut2.close();
		fOut2.close();

		/*System.out.println("-----------------------------------");
		System.out.println("Found "+manager.getAmountOfCountries()+" countries");
		container.dumpCountries();*/
		
		
	}

}
