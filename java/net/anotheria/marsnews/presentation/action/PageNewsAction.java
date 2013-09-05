package net.anotheria.marsnews.presentation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.anoplass.api.util.paging.PagingControl;
import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.presentation.bean.NewsItemBean;
import net.anotheria.util.slicer.Segment;
import net.anotheria.util.slicer.Slice;
import net.anotheria.util.slicer.Slicer;

public class PageNewsAction extends BaseNewsAction{

	@Override
	public ActionForward execute(ActionMapping mapping, FormBean formBean,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		List<NewsItemBean> beans = (List<NewsItemBean>)req.getSession().getAttribute("-entries-");
		if (beans==null){
			res.sendRedirect("news?ts="+System.currentTimeMillis());
			return null;
		}
		
		int page = 1; 
		try{
			page = Integer.parseInt(req.getParameter("page"));
		}catch(NumberFormatException e){}
		
		Slice<NewsItemBean> slice = Slicer.slice(new Segment(page, 20), beans);
		addBeanToSession(req, "entries", slice.getSliceData());
		PagingControl pc = new PagingControl(page, 20, slice.getTotalNumberOfItems());
		System.out.println("PAGE: "+page+", pc: "+pc);
		addBeanToRequest(req, "paging", pc);
		return mapping.findForward("success");
	}

	public String getClanLinkTarget(){
		return "newsForClan";
	}

}
