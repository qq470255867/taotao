package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.pojo.SearchResult;

public interface SearchDao {
	
	SearchResult searchDao (SolrQuery solrQuery) throws SolrServerException;

}
