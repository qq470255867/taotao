package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	
	public TaotaoResult getItemInfo(long  id);
	
	public TaotaoResult getItemDesc(long id);
	
	public TaotaoResult getItemParam(long id);

}
