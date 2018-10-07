package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceimpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public List<TbContent> getContentList(long contentCid) {
		// 查询商品列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list =contentMapper.selectByExample(example);
		
		return list;
	}

}
