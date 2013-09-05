package net.anotheria.marsnews.marsconnector;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class HttpClientFactory {
	private HttpClientProperties properties;
	
	public HttpClientFactory(HttpClientProperties someProperties){
		properties = someProperties;
	}
	
	public HttpClient createClient(){
		HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getParams().setParameter(HttpClientParams.USER_AGENT, properties.getUserAgent());
		return client;
	}
	
	public GetMethod createGetMethod(String url){
		GetMethod m = new GetMethod(url);
		if (properties.getReferer()!=null && properties.getReferer().length()>0)
			m.addRequestHeader(new Header("Referer", properties.getReferer()));
		return m;
	}
	
	public PostMethod createPostMethod(String url){
		PostMethod m = new PostMethod(url);
		if (properties.getReferer()!=null && properties.getReferer().length()>0)
			m.addRequestHeader(new Header("Referer", properties.getReferer()));
		return m;
	}
}
