package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {
	int 数字;

	@Autowired
	private SolrServer solrServer;

	@Override

	public SearchResult searchDao(SolrQuery solrQuery) throws SolrServerException {

		SearchResult searchResult = new SearchResult();

		// 根据查询结果查询索引库
		QueryResponse queryResponse = solrServer.query(solrQuery);

		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

		long numFound = solrDocumentList.getNumFound();

		searchResult.setRecordCount(numFound);

		List<Item> list = new ArrayList<>();

		for (SolrDocument solrDocument : solrDocumentList) {
			Item item = new Item();
			item.setId((String) solrDocument.get("id"));
			List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
			String str = "";
			if (list2 != null && list2.size() > 0) {
				str = list2.get(0);

			} else {
				str = (String) solrDocument.get("item_category_name");
			}

			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setTitle((String) solrDocument.get("item_title"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			list.add(item);

		}
		searchResult.setList(list);
		return searchResult;

	}
	

}

