package com.taotao.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.taotao.portal.pojo.CatSelectResult;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.service.CatItemListService;

@Controller
public class CatItemListController {

	@Autowired
	CatItemListService catItemListService;

	@RequestMapping("/products/{Cid}")
	public String showSelectPage(@PathVariable("Cid") Long Cid, @RequestParam(defaultValue = "1") Integer page,
			Model model) {

		CatSelectResult data = catItemListService.getCatItemList(Cid);
		
		try {
			
			

			if (data.getList()!=null) {

				List<Item> list = data.getList();
				if (list.size() < 10) {
					model.addAttribute("itemList", list);
				} else {

					List<Item> list2 = list.subList((page - 1) * 9, page * 9);
					model.addAttribute("itemList", list2);
				}

				return "products";

			}else {
				model.addAttribute("msg", data.getMsg());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "products";

	}

}
