package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
@Service
public class RedisServiceImpl implements RedisService {
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Autowired
	private JedisClient jedisClient;
	

	@Override
	public TaotaoResult syncContent(Long contentCid) {
		
		try {
			
			jedisClient.hdel("INDEX_CONTENT_REDIS_KEY",contentCid+"");
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, com.taotao.common.utils.ExceptionUtil.getStackTrace(e));
			
		}
		// TODO Auto-generated method stub
		return TaotaoResult.ok();
	}

}
