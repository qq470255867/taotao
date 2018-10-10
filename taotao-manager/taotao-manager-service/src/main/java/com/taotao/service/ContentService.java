package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	
	EUDataGridResult getContentBycategoryId(int page,int rows,long categoryId);
	
	TaotaoResult insertContent(TbContent content);
	
	TaotaoResult deleteContentById(long ids);

}
