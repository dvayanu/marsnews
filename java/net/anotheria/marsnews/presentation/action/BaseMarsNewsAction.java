package net.anotheria.marsnews.presentation.action;

import net.anotheria.maf.action.Action;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.marsnews.ranks.business.RankServiceFactory;
import net.anotheria.marsnews.ranks.business.RanksService;
import net.anotheria.moskito.core.dynamic.MoskitoInvokationProxy;
import net.anotheria.moskito.core.predefined.ServiceStatsCallHandler;
import net.anotheria.moskito.core.predefined.ServiceStatsFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseMarsNewsAction implements Action{
	private static AtomicInteger instanceCounter = new AtomicInteger();
	private static RanksService ranksService = (RanksService)createServiceMonitoringInstance(RanksService.class, RankServiceFactory.createRanksService());
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	protected static RanksService getRanksService(){
		return ranksService;
	}
	
	protected static Object createServiceMonitoringInstance(Class inter, Object service){
		MoskitoInvokationProxy proxy = new MoskitoInvokationProxy(
				service,
				new ServiceStatsCallHandler(),
				new ServiceStatsFactory(),
				inter.getName().substring(inter.getName().lastIndexOf('.')+1)+"-"+instanceCounter.getAndIncrement(),
				"service",
				"default",
				inter
			);
		Object ret =  proxy.createProxy();
		return ret;
		
	}
	
	protected void addBeanToRequest(HttpServletRequest req, String attributeName, Object attribute){
		req.setAttribute(attributeName, attribute);
	}

	protected void addBeanToSession(HttpServletRequest req, String attributeName, Object attribute){
		req.getSession().setAttribute(attributeName, attribute);
	}

	@Override
	public void preProcess(ActionMapping mapping, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postProcess(ActionMapping mapping, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
