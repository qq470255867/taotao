package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping("/itemInfo/{itemId}")
	@ResponseBody
	public TaotaoResult getItemInfo(@PathVariable("itemId") Long itemId) {
		TaotaoResult itemInfo = itemService.getItemInfo(itemId);
		return itemInfo;

	}

	@RequestMapping("/itemDesc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable("itemId") Long itemId) {
		TaotaoResult itemDesc = itemService.getItemDesc(itemId);
		return itemDesc;

	}
	@RequestMapping("/itemParam/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable("itemId") Long itemId) {
		TaotaoResult itemParam = itemService.getItemParam(itemId);
		return itemParam;

	}
}
