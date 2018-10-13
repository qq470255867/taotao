package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	//导入所有商品数据
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItem(){		
		TaotaoResult result=itemService.importAllItem();
		return result;
		
	}
	

}
