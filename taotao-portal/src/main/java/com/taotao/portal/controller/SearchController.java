package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	@RequestMapping("/search")
	public String showSearch(@RequestParam("q") String query, @RequestParam(defaultValue = "1") Integer page,
			Model model) {
		if (query!=null) {
			
			try {
				query = new String(query.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		SearchResult result = searchService.getSearchResult(query, page);
		
		model.addAttribute("query", query);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("page", page);
		model.addAttribute("itemList", result.getList());

		return "search";
	}

}
