package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemServiceImpl;

@Controller
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;

	@RequestMapping("/item/{ItemId}")
	public String showPage(@PathVariable Long ItemId, Model model) {

		ItemInfo item = itemService.getItemInfo(ItemId);
		model.addAttribute("item",item);

		return "item";

	}

}
