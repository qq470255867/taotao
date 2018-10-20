package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.rest.pojo.Item;
@Service
public class CatItemListServiceImpl implements CatItemListService {

	@Autowired
	TbItemMapper itemMapper;

	@Override
	public List<TbItem> getCatItemListService(long Cid) {

		TbItemExample example = new TbItemExample();

		Criteria criteria = example.createCriteria();

		criteria.andCidEqualTo(Cid);

		List<TbItem> list = itemMapper.selectByExample(example);

		return list;
	}

}
