package net.anotheria.marsnews.marsconnector;

import net.anotheria.marsnews.shared.config.ConfigFactory;
import net.anotheria.util.Date;
import net.anotheria.util.NumberUtils;

public abstract class MarsRequest {
	
	private int serverId;
	
	protected MarsRequest(){
		serverId = ConfigFactory.getConfig().getServerId();
	}
	
	protected String toSOAPDate(long aDate){
		String ret = "";
		Date d = new Date(aDate);
		
		ret += NumberUtils.itoa(d.getYear(),4);
		ret += "-";
		ret += NumberUtils.itoa(d.getMonth(), 2);
		ret += "-";
		ret += NumberUtils.itoa(d.getDay(), 2);
		/*ret += " ";
		ret += NumberUtils.itoa(d.getHour(), 2);
		ret += ":";
		ret += NumberUtils.itoa(d.getMin(), 2);*/
		return ret;
	}
	
	 //"2007-05-10 12:41:08Z"
	protected static String toASPDate(long aDate){
		String ret = "";
		Date d = new Date(aDate);
		
		ret += NumberUtils.itoa(d.getYear(),4);
		ret += "-";
		ret += NumberUtils.itoa(d.getMonth(), 2);
		ret += "-";
		ret += NumberUtils.itoa(d.getDay()	, 2); 
		ret += " ";
		ret += NumberUtils.itoa(0/*d.getHour()*/, 2);
		ret += ":";
		ret += NumberUtils.itoa(0/*d.getMin()*/, 2);
		ret += ":";
		ret += NumberUtils.itoa(0, 2);
		ret += "Z";
		
		
		return ret;
	}
	
	protected String writeString(String s){
		return s+"\n";
	}
	
	protected String quote(String s){
		return "\""+s+"\"";
	}
	
	private static final String HEX_DIGITS = "0123456789abcdef";

	protected static String toHexString(byte[] v)	{
		StringBuffer sb = new StringBuffer(v.length * 2);
		for (int i = 0; i < v.length; i++) {
			int b = v[i] & 0xFF;
			sb.append(HEX_DIGITS.charAt(b >>> 4));
			sb.append(HEX_DIGITS.charAt(b & 0xF));
		}
		return sb.toString();
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public abstract String toXML();
	
	public abstract String getAction();


}
