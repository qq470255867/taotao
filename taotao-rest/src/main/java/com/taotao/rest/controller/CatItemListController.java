package com.taotao.rest.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.CatItemListService;

@Controller
public class CatItemListController {
	
	@Autowired
	CatItemListService catItemListService;
	
	
	@RequestMapping("/selectByCid/{Cid}")
	@ResponseBody
	public TaotaoResult selectByCid(@PathVariable("Cid") Long Cid){
		
		try {
			List<TbItem> list = catItemListService.getCatItemListService(Cid);
			
			
			return TaotaoResult.ok(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return TaotaoResult.build(500, com.taotao.common.utils.ExceptionUtil.getStackTrace(e));
		}
		
		
		
	}
	

}
