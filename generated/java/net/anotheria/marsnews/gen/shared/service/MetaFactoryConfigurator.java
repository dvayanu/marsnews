/**
 ********************************************************************************
 *** MetaFactoryConfigurator.java                                             ***
 *** Generator: net.anotheria.asg.generator.ConfiguratorGenerator             ***
 *** generated by AnoSiteGenerator (ASG), Version: 1.3.3                      ***
 *** Copyright (C) 2005 - 2010 Anotheria.net, www.anotheria.net               ***
 *** All Rights Reserved.                                                     ***
 ********************************************************************************
 *** Don't edit this code, if you aren't sure                                 ***
 *** that you do exactly know what you are doing!                             ***
 *** It's better to invest time in the generator, as into the generated code. ***
 ********************************************************************************
 */

package net.anotheria.marsnews.gen.shared.service;

import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.marsnews.gen.rankedcountries.service.IRankedCountriesService;
import net.anotheria.marsnews.gen.rankedcountries.service.RankedCountriesServiceFactory;

public class MetaFactoryConfigurator{

	private static volatile boolean configured;

	public static void configure(){
		if (configured)
			return;
		configured = true;
		// //aliases for IRankedCountriesService
		MetaFactory.addAlias(IRankedCountriesService.class, Extension.LOCAL);
		MetaFactory.addAlias(IRankedCountriesService.class, Extension.DOMAIN, Extension.LOCAL);
		MetaFactory.addAlias(IRankedCountriesService.class, Extension.DOMAIN, Extension.EDITORINTERFACE);
		MetaFactory.addAlias(IRankedCountriesService.class, Extension.DB, Extension.DOMAIN);
		MetaFactory.addFactoryClass(IRankedCountriesService.class, Extension.DB, RankedCountriesServiceFactory.class);
	}
}
