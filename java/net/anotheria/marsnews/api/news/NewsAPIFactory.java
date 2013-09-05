package net.anotheria.marsnews.api.news;

import net.anotheria.anoplass.api.APIFactory;

public class NewsAPIFactory implements APIFactory<NewsAPI>{

	@Override
	public NewsAPI createAPI() {
		return new NewsAPIImpl();
	}
	
}
