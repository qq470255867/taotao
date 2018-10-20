package com.taotao.sso.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
@Service
public class JedisClientCluster implements JedisClient {
	
	@Autowired
	JedisCluster jedisCluster;

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public long incr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.incr(key);
	}

	@Override
	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(hkey, key);
	}

}
