package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, int page, int rows) throws SolrServerException {
		
		SolrQuery query = new SolrQuery();
		
		query.setQuery(queryString);
		
		query.setStart((page-1)*rows);
		
		query.set("df", "item_keywords");
		
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		SearchResult searchResult = searchDao.searchDao(query);
		
		long recordCount =searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		
		if (recordCount % rows>0) {
			
			pageCount++;
			
		}		
		searchResult.setPageCount(pageCount);
		
		searchResult.setCurPage(page);
		
		return searchResult;
	}

}
