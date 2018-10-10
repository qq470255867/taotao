package com.taotao.rest.jedis;

import java.util.HashSet;

import javax.sound.sampled.Port;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

public class JedisTest {
	@Test
	public void test() {
		Jedis jedis = new Jedis("192.168.19.131", 6379);
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();

	}

	@Test
	public void pooltest() {
		JedisPool pool = new JedisPool("192.168.19.131", 6379);
		Jedis jedis = pool.getResource();
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
		pool.close();
	}

	@Test
	public void clustertest() {
		HashSet<HostAndPort> nodes =new HashSet<>();
		nodes.add(new HostAndPort("192.168.19.131",7001));
		nodes.add(new HostAndPort("192.168.19.131",7002));
		nodes.add(new HostAndPort("192.168.19.131",7003));
		nodes.add(new HostAndPort("192.168.19.131",7004));
		nodes.add(new HostAndPort("192.168.19.131",7005));
		nodes.add(new HostAndPort("192.168.19.131",7006));
		

		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		String str = cluster.get("key1");
		System.out.println(str);
		cluster.close();
	}
	@Test
	//单机版测试
	public void SpringJedisalone(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
		
				
	}
	
	//集群测试
	@Test
	public void SpringJediscluster(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}

}
