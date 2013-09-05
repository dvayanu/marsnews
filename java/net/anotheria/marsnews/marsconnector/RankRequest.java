package net.anotheria.marsnews.marsconnector;

import static net.anotheria.marsnews.marsconnector.MarsConstants.SD;
import static net.anotheria.marsnews.marsconnector.MarsConstants.SECRET;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.anotheria.util.Date;

public class RankRequest extends MarsRequest{
	
	private int today;
	
	public RankRequest(){
		long time = System.currentTimeMillis()-1000L*60*60*2;
		Date d =  new Date(time); 
		today = d.getDay();
		System.out.println("Today: "+today);
	}
	
	public int getToday() {
		return today;
	}

	public void setToday(int today) {
		this.today = today;
	}

	public String toXML(){
		String ret = "";
		ret += writeString("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ret += writeString("<soap:Envelope xmlns:xsi="+quote("http://www.w3.org/2001/XMLSchema-instance")+" xmlns:xsd="+quote("http://www.w3.org/2001/XMLSchema")+" xmlns:soap="+quote("http://schemas.xmlsoap.org/soap/envelope/")+">");
		ret += writeString("  <soap:Body>");
		ret += writeString("    <ProvideRank xmlns="+quote("http://tempuri.org/")+">");
		ret += writeString("		      <Today>"+getToday()+"</Today>");
		ret += writeString("              <Hash>"+getHash()+"</Hash>");
		ret += writeString("		      <ServerId>"+getServerId()+"</ServerId>");
		ret += writeString("    </ProvideRank>");
		ret += writeString("  </soap:Body> ");
		ret += writeString("</soap:Envelope>");
		return ret;
		
	}
	

	private String getHash(){
		//"blankdot" + ":" + Start.ToString("u") + ":" + Count + ":" + Rank + ":" + AttackType + ":" + Player + ":" + CountryName + ":" + Clan;
		String in = SECRET;
		in += SD;
		in += getToday();
		in += SD;
		in += getServerId();
		
		System.out.println("In for md5: "+in);
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(in.getBytes());
			return toHexString(md5.digest()).toUpperCase();
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException("No md5");
		}
	}

	public String getAction(){
		return "ProvideRank";
	}

}
