package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//内容分类管理
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatgoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<EUTreeNode> list = contentCatgoryService.getContentCatgory(parentId);
		return list;
	}

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCatgory(Long parentId, String name) {
		TaotaoResult insertContentCatgory = contentCatgoryService.insertContentCatgory(parentId, name);
		return insertContentCatgory;
	}

}
