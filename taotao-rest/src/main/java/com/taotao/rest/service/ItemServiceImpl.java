package com.taotao.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	TbItemMapper itemMapper;

	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Autowired
	TbItemParamItemMapper itemParamItem;

	@Autowired
	JedisClient jedisClient;

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	

	@Override
	public TaotaoResult getItemInfo(long id) {

		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + id + " :base");

			if (!StringUtils.isBlank(json)) {

				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 先从缓存中取商品信息

		// TODO Auto-generated method stub
		TbItem item = itemMapper.selectByPrimaryKey(id);

		// 没有则从数据库添加进缓存

		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + id + " :base", JsonUtils.objectToJson(item));

			jedisClient.expire(REDIS_ITEM_KEY + ":" + id + " :base", REDIS_ITEM_EXPIRE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(long id) {

		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + id + " :desc");

			if (!StringUtils.isBlank(json)) {

				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + id + " :desc", JsonUtils.objectToJson(itemDesc));

			jedisClient.expire(REDIS_ITEM_KEY + ":" + id + " :desc", REDIS_ITEM_EXPIRE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParam(long id) {

		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + id + " :param");

			if (!StringUtils.isBlank(json)) {

				TbItemParamItem item = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 先从缓存中取商品信息

		// TODO Auto-generated method stub
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		
		List<TbItemParamItem> result = itemParamItem.selectByExampleWithBLOBs(example);
		
		if (result!=null && result.size()>0) {
			
			TbItemParamItem param = result.get(0);
			
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + id + " :param", JsonUtils.objectToJson(param));
				
				jedisClient.expire(REDIS_ITEM_KEY + ":" + id + " :param", REDIS_ITEM_EXPIRE);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaotaoResult.ok(param);
			
		}else {
			return TaotaoResult.build(400, "查无此信息");
		}
		
		
		// 没有则从数据库添加进缓存


	}

}
