package test;

import java.util.List;

import net.anotheria.marsnews.marsconnector.parser.XMLParser;
import net.anotheria.marsnews.news.business.NewsEntry;
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
import net.anotheria.util.IOUtils;

public class DumpFile {
	
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
		String content = IOUtils.readFileAtOnceAsString("data-15.04.2007.xml");
		//System.out.println("Read: "+content.length()+" bytes");
		
		List<NewsEntry> entries = new XMLParser().parseNewsResponse(content);
		System.out.println("Parsed "+entries.size()+" news entries");
		
		System.out.println(entries.get(0));
		if (1==1)
			return;
		StatsContainer container = setupContainer();
		CountryManager manager = CountryManager.getInstance();
		long start = System.currentTimeMillis();
		for (NewsEntry e : entries){
			manager.process(e);
			container.process(e);
		}
		long end = System.currentTimeMillis();
		System.out.println("Parsed "+entries.size()+" entries in "+(end-start)+" ms.");
		
		System.out.println("Done:");
		container.dumpClans();
		System.out.println("-----------------------------------");
		System.out.println("Found "+manager.getAmountOfCountries()+" countries");
		container.dumpCountries();
		
		
	}
}
