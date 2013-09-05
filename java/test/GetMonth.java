package test;

import java.io.FileOutputStream;

import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.NewsRequest;
import net.anotheria.util.Date;
import net.anotheria.util.DateUtility;
import net.anotheria.util.IOUtils;
import net.anotheria.util.NumberUtils;
import net.anotheria.util.StringUtils;

public class GetMonth {
	private static HttpClientProperties properties ;
	static{
		String referer = "";
		String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";

		properties = new HttpClientProperties();
		properties.setReferer(referer);
		properties.setUserAgent(userAgent);
		
	}
	
	public static void main(String a[]){
		int month = 5;
		
		Date d = new Date(10, month, 2007);
		//System.out.println(d);
		while(Date.isValid(d) && (d.getMonth()==month || d.getDay()==1)){
			//do something
			try{
				process(d);
				try{
					System.out.println("... sleeping ...");
					Thread.sleep(1000*60*1);
				}catch(InterruptedException e){}
			}catch(Exception e){
				System.out.println(d+" aborted. ");
				e.printStackTrace();
			}
			d = DateUtility.nextDate(d);
			//System.out.println(d);
		}
	}
	
	private static void process(Date d)throws Exception{
		System.out.println("processing: "+d);
		NewsRequest request = new NewsRequest();
		request.setCount(2);
		//request.setDate(d.toMill());
		

		HttpGetter g = new HttpGetter(properties);
		HttpResult result = g.post(request);
		System.out.println("ResultCode: "+result.getResultCode()+", "+result.getData().length+" bytes");
		//System.out.println("Headers: "+result.getHeaders());
		//System.out.println("Server replied: "+result.getDataAsString());
		FileOutputStream fOut = new FileOutputStream("data-"+NumberUtils.makeDigitalDateString(d.toMill())+".xml");
		fOut.write(result.getData());
		fOut.flush();
		fOut.close();

			
	}
}
