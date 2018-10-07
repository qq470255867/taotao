package com.taotao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {
	
	@Autowired 
	TbContentCategoryMapper contentCategoryMapper;
	

	@Override
	
	public List<EUTreeNode> getContentCatgory(long parentId) {
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list =contentCategoryMapper.selectByExample(example);
		List< EUTreeNode> resultlist= new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			resultlist.add(node);
			
		}
		return resultlist;
	}


	@Override
	public TaotaoResult insertContentCatgory(long parentId, String name) {
		// 添加节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isparent是否为true，不为ture则改为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
			
		}
		return TaotaoResult.ok(contentCategory);
	}

}
