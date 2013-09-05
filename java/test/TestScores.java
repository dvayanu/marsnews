package test;

import java.io.FileOutputStream;
import java.util.List;

import net.anotheria.marsnews.gen.rankedcountries.data.RankedCountry;
import net.anotheria.marsnews.marsconnector.HttpClientProperties;
import net.anotheria.marsnews.marsconnector.HttpGetter;
import net.anotheria.marsnews.marsconnector.HttpResult;
import net.anotheria.marsnews.marsconnector.RankRequest;
import net.anotheria.marsnews.marsconnector.parser.XMLParser;
import net.anotheria.marsnews.ranks.business.RanksService;
import net.anotheria.marsnews.ranks.business.RanksServiceImpl;

public class TestScores {
	public static void main(String a[]) throws Exception{
		//BasicConfigurator.configure();
		RankRequest r = new RankRequest();
		
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
		FileOutputStream fOut = new FileOutputStream("test-scores.xml");
		fOut.write(result.getData());
		fOut.flush();
		fOut.close();
		
				
		List<RankedCountry> countries = new XMLParser().parseRanksResponse(result.getDataAsString());
		RanksService service = new RanksServiceImpl();
		service.updateRanks(countries);
		System.out.println("done");
		
	}
	
	private static void test1(){
		
	}
}
