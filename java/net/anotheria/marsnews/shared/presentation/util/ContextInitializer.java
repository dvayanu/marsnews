package net.anotheria.marsnews.shared.presentation.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.anotheria.marsnews.util.NewsUpdater;
import net.anotheria.marsnews.util.RanksUpdater;
import net.anotheria.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * This listener performs the webapp initialization upon webserver start (or webapp hot-deploy).
 * @author lrosenberg
 * @created Feb 16, 2007
 */
public class ContextInitializer implements ServletContextListener{
	
	
	private NewsUpdater newsUpdater;
	private RanksUpdater ranksUpdater;
	private static Logger log;
	/**
	 * Initializes log4j, configures MetaFactory.
	 */
	static {
		//System.out.println("new file: "+new File(".").getAbsolutePath());
		//PropertyConfigurator.configureAndWatch("webapps/ROOT/WEB-INF/classes/log4j.properties");
		DOMConfigurator.configureAndWatch("webapps/ROOT/WEB-INF/classes/log4j.xml");
		log = Logger.getLogger(ContextInitializer.class);
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		log.info("Stoping updater...");
		try{
			newsUpdater.stopRunning();
			ranksUpdater.stopRunning();
		}catch(Exception e){
			log.error("Stoping updater failed!");
		}
		log.info("MARSNEWS: CONTEXT DESTROYED @ "+Date.currentDate());
		
	}

	public void contextInitialized(ServletContextEvent event) {
		log.info("MARSNEWS: CONTEXT INITIALIZED @ "+Date.currentDate());
		newsUpdater = new NewsUpdater();
		newsUpdater.start();
		log.info("News Updater started");
		try{
			ranksUpdater = new RanksUpdater();
			ranksUpdater.start();
			log.info("Ranks Updater started");
		}catch(Exception e){
			log.error("Ranks updater start failed, stoping..." ,e);
			throw new RuntimeException("Aborted");
		}
		
	}
	 
}
