package net.anotheria.marsnews.presentation;

import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import net.anotheria.maf.MAFFilter;
import net.anotheria.maf.action.ActionMappingsConfigurator;

import org.apache.log4j.Logger;

public class MarsNewsFilter extends MAFFilter{
	
	private static final Logger log = Logger.getLogger(MarsNewsFilter.class);
	
	
	@Override public void init(FilterConfig config) throws ServletException {
		super.init(config);

		log.info("Initing MarsNewsFilter...");
		System.out.println("Initing MarsNewsFilter...");
	}

	@Override
	protected List<ActionMappingsConfigurator> getConfigurators(){
		return Arrays.asList(new ActionMappingsConfigurator[]{ new MarsNewsMappingsConfigurator() });
	}

}
