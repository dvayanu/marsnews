package net.anotheria.marsnews.presentation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.marsnews.util.RanksUpdater;

public class ForceRanksUpdate extends BaseMarsNewsAction {

	private RanksUpdater ranksUpdater = new RanksUpdater();
	
	@Override
	public ActionForward execute(ActionMapping mapping, FormBean formBean,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ranksUpdater.runUpdate();
		
		res.getOutputStream().write("Ok".getBytes());
		res.getOutputStream().close();
		return null;
	}
	
	
	
}
