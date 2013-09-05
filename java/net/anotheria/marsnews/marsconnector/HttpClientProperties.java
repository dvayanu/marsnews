package net.anotheria.marsnews.marsconnector;

public class HttpClientProperties {
	private String userAgent;
	private String referer;
	
	public String toString(){
		return "Useragent: "+userAgent+", Referer: "+referer;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
