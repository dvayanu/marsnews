package net.anotheria.marsnews.presentation.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.news.business.CombiNewsFilter;
import net.anotheria.marsnews.news.business.NewsEntry;
import net.anotheria.marsnews.presentation.bean.NewsItemBean;

public class XMLFeedAction extends BaseNewsAction{

	public static final String SECRET = "no-id";
	
	public static final String PARAM_SINCE = "pSince";
	
	public static final long MARS_OFFSET = 1000L*60*60*2; //2 hours
	//public static final long NO_SHOW_PERIOD = 1000L*60*15; // 15 minutes
	public static final long DEF_SINCE_OFFSET = 1000L*60*60*24; // 24 hours
	
	@Override
	protected String getClanLinkTarget() {
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean arg1, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		CombiNewsFilter filter = createFilterFromRequest(req);
		long now = System.currentTimeMillis() - MARS_OFFSET;
		long notBefore = now ;//- NO_SHOW_PERIOD;
		long since = now - DEF_SINCE_OFFSET;
		try{
			now = Long.parseLong(req.getParameter("pSince"));
		}catch(Exception ignored){}
		if (since<(now - DEF_SINCE_OFFSET))
			since = now - DEF_SINCE_OFFSET;
		filter.setTimestampSince(since);
		
		List<NewsEntry> entries = getNewsService().getFilteredNews(filter);
		List<NewsItemBean> beans = new ArrayList<NewsItemBean>(entries.size());
		for (NewsEntry entry : entries){
			NewsItemBean b = makeNewsItemBean(entry);
			b.setObfuscatedId(getHash(entry));
			beans.add(b);
		}
		
		addBeanToRequest(req, "entries", beans);

		
		return mapping.findForward("success");
	}
	
	private String getHash(NewsEntry e){
		
		String in = SECRET+":"+(e.getId()*99);
		
		//System.out.println("In for md5: "+in);
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(in.getBytes());
			return toHexString(md5.digest()).toUpperCase();
		}catch(NoSuchAlgorithmException x){
			throw new RuntimeException("No md5");
		}
	}
	
	private static final String HEX_DIGITS = "0123456789abcdef";

	private static String toHexString(byte[] v)	{
		StringBuffer sb = new StringBuffer(v.length * 2);
		for (int i = 0; i < v.length; i++) {
			int b = v[i] & 0xFF;
			sb.append(HEX_DIGITS.charAt(b >>> 4));
			sb.append(HEX_DIGITS.charAt(b & 0xF));
		}
		return sb.toString();
	}


}
