/**
 ********************************************************************************
 *** RankedCountryXMLHelper.java                                              ***
 *** generated by AnoSiteGenerator (ASG), Version: 1.3.3                      ***
 *** Copyright (C) 2005 - 2010 Anotheria.net, www.anotheria.net               ***
 *** All Rights Reserved.                                                     ***
 ********************************************************************************
 *** Don't edit this code, if you aren't sure                                 ***
 *** that you do exactly know what you are doing!                             ***
 *** It's better to invest time in the generator, as into the generated code. ***
 ********************************************************************************
 */

package net.anotheria.marsnews.gen.rankedcountries.data;

import net.anotheria.util.xml.XMLNode;
import net.anotheria.util.xml.XMLAttribute;
import net.anotheria.asg.data.XMLHelper;
import net.anotheria.asg.data.MultilingualObject;

public class RankedCountryXMLHelper{

		public static final String[] LANGUAGES = new String[]{"de","en"};
		private static XMLNode _toXML(RankedCountry object, String[] languages){
			XMLNode ret = new XMLNode("RankedCountry");
			ret.addAttribute(new XMLAttribute("id", object.getId()));

			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("rank", null, object.getRank()	));
			ret.addChildNode(XMLHelper.createXMLNodeForStringValue("countryName", null, object.getCountryName()	));
			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("countryId", null, object.getCountryId()	));
			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("land", null, object.getLand()	));
			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("networth", null, object.getNetworth()	));
			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("color", null, object.getColor()	));
			ret.addChildNode(XMLHelper.createXMLNodeForStringValue("clan", null, object.getClan()	));
			ret.addChildNode(XMLHelper.createXMLNodeForIntValue("gov", null, object.getGov()	));
			ret.addChildNode(XMLHelper.createXMLNodeForBooleanValue("gdi", null, object.getGdi()	));
			ret.addChildNode(XMLHelper.createXMLNodeForBooleanValue("dead", null, object.getDead()	));
			ret.addChildNode(XMLHelper.createXMLNodeForBooleanValue("protection", null, object.getProtection()	));


			if(object instanceof MultilingualObject){
				MultilingualObject multilangDoc = (MultilingualObject) object;
				ret.addChildNode(XMLHelper.createXMLNodeForBooleanValue("multilingualDisabled", null, multilangDoc.isMultilingualDisabledInstance()));
			}
			return ret;
		}

		public static XMLNode toXML(RankedCountry object){
			return _toXML(object, LANGUAGES);
		}

		public static XMLNode toXML(RankedCountry object, String... languages){
			if (languages==null || languages.length==0)
				return toXML(object);
			return _toXML(object, languages);
		}

		public static RankedCountry fromXML(XMLNode node){
			return null;
		}

}
