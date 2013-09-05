package test;

import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.NewsRequest;

import java.io.FileOutputStream;

public class Test {
	public static void main(String a[]) throws Exception{
		//BasicConfigurator.configure();
		NewsRequest r = new NewsRequest();
		r.setLastId(1);
		r.setCount(1);
		
		System.out.println(r.toXML());
/*		if (1==1)
			return;*/

		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		HttpClientProperties properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);

		HttpGetter g = new HttpGetter(properties);
		HttpResult result = g.post(r);
		System.out.println("ResultCode: "+result.getResultCode());
		System.out.println("Headers: "+result.getHeaders());
		System.out.println("Server replied: "+result.getDataAsString());
		FileOutputStream fOut = new FileOutputStream("test-x2.xml");
		fOut.write(result.getData());
		fOut.flush();
		fOut.close();
		
				
		
	}
	
	private static void test1(){
		
	}
}
