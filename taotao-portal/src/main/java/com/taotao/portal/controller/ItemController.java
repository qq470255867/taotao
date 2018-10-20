package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.CatName;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.CatService;
import com.taotao.portal.service.ItemServiceImpl;

@Controller
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	CatService catService;

	@RequestMapping("/item/{ItemId}")
	public String showPage(@PathVariable Long ItemId, Model model) {

		ItemInfo item = itemService.getItemInfo(ItemId);
		
		TbItemDesc desc = itemService.geTbItemDesc(ItemId);
		
		TbItemParamItem itemParam = itemService.getItemParam(ItemId);
		
		CatName catName = catService.getCatName(ItemId);
		model.addAttribute("item",item);
		model.addAttribute("catName",catName);
		model.addAttribute("itemDesc",desc);
		
		model.addAttribute("itemParam",itemParam);
		

		return "item";

	}


}
