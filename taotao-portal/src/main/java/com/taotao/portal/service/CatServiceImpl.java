package com.taotao.portal.service;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.CatName;
@Service
public class CatServiceImpl implements CatService {

	@Override
	public CatName getCatName(long id) {
		//http://localhost:8081/rest/catgories/816753
		
	    String httpResult = HttpClientUtil.doGet("http://localhost:8081/rest/catgories/"+id);
		
	    TaotaoResult formatToPojo = TaotaoResult.formatToPojo(httpResult, CatName.class);

	    CatName data = (CatName) formatToPojo.getData();

		return data;
	    
	
	}

}
