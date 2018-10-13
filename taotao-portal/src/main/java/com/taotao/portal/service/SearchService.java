package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

public interface SearchService {
	
     public SearchResult getSearchResult(String query,int page);
}
