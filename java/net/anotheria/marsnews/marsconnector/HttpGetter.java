package net.anotheria.marsnews.marsconnector;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.Map;

public class HttpGetter {

	private HttpClientFactory factory;
	private HttpClient client;
	
	public HttpGetter(HttpClientProperties properties){
		factory = new HttpClientFactory(properties);
		client = factory.createClient();	
	}
	
	public HttpResult post(MarsRequest r){
		PostMethod m = null;
		try{
			m = factory.createPostMethod("http://www.mars2025.ru/earth.asmx");
			m.setRequestBody(r.toXML());
			m.addRequestHeader(new Header("SOAPAction","http://tempuri.org/"+r.getAction()));
			//m.addRequestHeader(new Header("Content-type", "text/plain"));
			m.addRequestHeader(new Header("Content-Type", "text/xml; charset=utf-8"));


			
			//System.out.println("Path: "+m.getPath());
			//System.out.println("Name: "+m.getName());
			/*if (1==1)
				return null;*/
			int resultCode = client.executeMethod(m);
			HttpResult result = new HttpResult(resultCode);
			Header hh[] = m.getResponseHeaders();
			for (Header h : hh)
				result.addHeader(h.getName(), h.getValue());
			//System.out.println("Result: "+result);
			//Header[] hh = m.getResponseHeaders();
			//printHeaders(hh);
			result.setData(m.getResponseBody());
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public HttpResult post(HtmlForm form){
		PostMethod m = null;
		try{
			m = factory.createPostMethod(form.getAction());
			Map<String, String> params = form.getFields();
			
			for (String n :params.keySet())
				m.addParameter(n, params.get(n));
			
			//System.out.println("Path: "+m.getPath());
			//System.out.println("Name: "+m.getName());
			/*if (1==1)
				return null;*/
			int resultCode = client.executeMethod(m);
			HttpResult result = new HttpResult(resultCode);
			Header hh[] = m.getResponseHeaders();
			for (Header h : hh)
				result.addHeader(h.getName(), h.getValue());
			//System.out.println("Result: "+result);
			//Header[] hh = m.getResponseHeaders();
			//printHeaders(hh);
			result.setData(m.getResponseBody());
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpResult get(String url){
		//System.out.println("==================================\ngetting url: "+url);
		GetMethod m = null;
		
		
		try{
			m = factory.createGetMethod(url);
			
			int resultCode = client.executeMethod(m);
			byte data[] = m.getResponseBody();
			HttpResult result = new HttpResult(resultCode);
			result.setData(data);
			Header hh[] = m.getResponseHeaders();
			for (Header h : hh)
				result.addHeader(h.getName(), h.getValue());
			//System.out.println("Client replied: "+resultCode);
			try{
				if (resultCode!=200)
					System.out.println("Data: "+new String(data));
			}catch(Exception e){}
			return result;
			//Header[] hh = m.getResponseHeaders();
			//printHeaders(hh);
		}catch(Exception e){	
			e.printStackTrace();
			if (m!=null){
				try{
					m.releaseConnection();
				}catch(Exception ignored){}
			}
		}
		m = null;
		
		return null;
	}
	
	private void printHeaders(Header[] headers){
		System.out.println("=== HEADERS ");
		for (Header h : headers){
			System.out.println(h.getName()+" = "+h.getValue());
			if (h.getName().equals("Set-Cookie")){
				System.out.println("=================== COOKIE: "+h.getName()+" = "+h.getValue());
			}
		}
		System.out.println("=== HEADERS ENDE");
	}
}
