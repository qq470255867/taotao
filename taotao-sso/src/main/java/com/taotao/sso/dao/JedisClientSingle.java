package com.taotao.sso.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.get(key);
		jedis.close();
		return str;

	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.set(key, value);
		jedis.close();
		return str;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.hget(hkey, key);
		jedis.close();
		return str;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long re = jedis.hset(key, key, value);
		jedis.close();
		return re;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long re = jedis.incr(key);
		jedis.close();
		return re;

	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		long re = jedis.expire(key, second);
		jedis.close();
		return re;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long re = jedis.ttl(key);
		jedis.close();
		return re;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		long re = jedis.del(key);
		jedis.close();
		return re;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		long re = jedis.hdel(hkey,key);
		jedis.close();
		return re;
	}

}
