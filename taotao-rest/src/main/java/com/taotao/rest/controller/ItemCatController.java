package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	ItemCatService itemCatService;

	@RequestMapping(value="/itemcat/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public String getItemcatList(String callback) {

		CatResult catResult = itemCatService.getItemCatList();
		// 把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		String result = callback + "(" + json + ");";
		return result;
	}

}
