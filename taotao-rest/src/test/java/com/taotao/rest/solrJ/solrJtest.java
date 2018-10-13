package com.taotao.rest.solrJ;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrJtest {
	@Test
	public void addDocument() throws SolrServerException, IOException {
		
		SolrServer server = new HttpSolrServer("http://192.168.19.131:8080/solr");
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		
		solrInputDocument.addField("id", "test01");
		solrInputDocument.addField("item_title", "测试商品01");
		solrInputDocument.addField("item_price", 888);
		
		server.add(solrInputDocument);	
		
		server.commit();
	}
	
	

}
