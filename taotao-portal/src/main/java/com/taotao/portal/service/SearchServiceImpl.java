package com.taotao.portal.service;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SOLR_BASE_URL}")
	public String SOLR_BASE_URL;
	


	@Override
	public SearchResult getSearchResult(String query, int page) {
		
		//http://localhost:8083/search/query?q=%22%E6%89%8B%E6%9C%BA%22
		
		Map<String, String> param=  new HashMap<>();
		param.put("q", query);
		param.put("page", page+"");
		//	
		String json = HttpClientUtil.doGet(SOLR_BASE_URL,param);
		
		TaotaoResult tresult = TaotaoResult.formatToPojo(json, SearchResult.class);
		
		SearchResult sresult = (SearchResult) tresult.getData();
		
		
		return sresult;
		
	}



}
