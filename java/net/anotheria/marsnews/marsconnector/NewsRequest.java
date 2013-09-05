package net.anotheria.marsnews.marsconnector;


import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import static net.anotheria.marsnews.marsconnector.MarsConstants.SECRET;
import static net.anotheria.marsnews.marsconnector.MarsConstants.SD;


public class NewsRequest extends MarsRequest{
	
	private int count;
	private int lastId;
	
	
	public NewsRequest(){
		lastId = 0;
		count = 200;
	}
	
	public NewsRequest(int aLastId, int aCount){
		lastId = aLastId;
		count = aCount;
	}


	
	public String toXML(){
		String ret = "";
		ret += writeString("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ret += writeString("<soap:Envelope xmlns:xsi="+quote("http://www.w3.org/2001/XMLSchema-instance")+" xmlns:xsd="+quote("http://www.w3.org/2001/XMLSchema")+" xmlns:soap="+quote("http://schemas.xmlsoap.org/soap/envelope/")+">");
		ret += writeString("  <soap:Body>");
		ret += writeString("    <ProvideNews xmlns="+quote("http://tempuri.org/")+">");
		ret += writeString("		      <LastId>"+lastId+"</LastId>");
		ret += writeString("		      <Count>"+count+"</Count>");
		ret += writeString("              <Hash>"+getHash()+"</Hash>");
		ret += writeString("		      <ServerId>"+getServerId()+"</ServerId>");
		ret += writeString("    </ProvideNews>");
		ret += writeString("  </soap:Body> ");
		ret += writeString("</soap:Envelope>");
		return ret;
		
	}
	
    //"blankdot" + ":" + Start.ToString("u") + ":" + Count + ":" + Rank + ":" + AttackType + ":" + Player + ":" + CountryName + ":" + Clan;

	private String getHash(){
		String in = SECRET;
		in += SD;
		in += getLastId();
		in += SD;
		in += getCount();
		in += SD;
		in += getServerId();
		
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(in.getBytes());
			return toHexString(md5.digest()).toUpperCase();
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException("No md5");
		}
	}
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	public int getLastId() {
		return lastId;
	}


	public void setLastId(int lastId) {
		this.lastId = lastId;
	}
	
	public String getAction(){
		return "ProvideNews";
	}
	
}
