package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.CatSelectResult;
import com.taotao.portal.service.CatItemListService;

@Controller
public class CatItemListController {

	@Autowired
	CatItemListService catItemListService;

	@RequestMapping("/products/{Cid}")
	public String showSelectPage(@PathVariable("Cid") Long Cid, 
			Model model) {
		


		CatSelectResult data = catItemListService.getCatItemList(Cid);
		
		if (!(data==null)) {
			
			model.addAttribute("itemList", data.getList());
		}
		

		return "products";
	}

}
