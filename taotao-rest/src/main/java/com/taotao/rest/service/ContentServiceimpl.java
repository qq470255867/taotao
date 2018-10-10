package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;

@Service
public class ContentServiceimpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(long contentCid) {
		try {
			String result = jedisClient.hget("INDEX_CONTENT_REDIS_KEY", contentCid + "");			
			if (!org.apache.commons.lang3.StringUtils.isBlank(result)) {
				List<TbContent> relist =JsonUtils.jsonToList(result, TbContent.class);
				return relist;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 查询商品列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		List<TbContent> list = contentMapper.selectByExample(example);

		// 查询结果添加缓存
		try {
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset("INDEX_CONTENT_REDIS_KEY", contentCid + "", cacheString);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	

}
