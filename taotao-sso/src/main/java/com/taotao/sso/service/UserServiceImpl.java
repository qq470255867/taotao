package com.taotao.sso.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;

import redis.clients.jedis.Jedis;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jdeisClient;
	
	@Value("${REDIS_USER_KEY_SESSION}")
	private String REDIS_USER_KEY_SESSION;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Override
	public TaotaoResult registerCheck(String param, Integer type) {

		TbUserExample example = new TbUserExample();

		Criteria criteria = example.createCriteria();

		if (type == 1) {

			criteria.andUsernameEqualTo(param);

		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);

		} else if (type == 3) {
			criteria.andEmailEqualTo(param);

		}

		List<TbUser> selectResult = userMapper.selectByExample(example);

		if (selectResult.size() == 0 || selectResult == null) {

			return TaotaoResult.ok(true);

		}
		return TaotaoResult.ok(false);

	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		
		user.setCreated(new Date());
		user.setUpdated(new Date());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult UserLogin(String username, String password) {
		
		TbUserExample example = new TbUserExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(username);
		
		
		
		List<TbUser> list = userMapper.selectByExample(example);
		
		if (list==null||list.size()==0) {
			
			return TaotaoResult.build(400, "数据库中没有此用户");
			
			
		}
		String asHex = DigestUtils.md5DigestAsHex(password.getBytes());
		
		TbUser user = list.get(0);
		
		//不是同一个引用地址，所以不能用==
		if (!(user.getPassword().equals(asHex))) {
			
			return TaotaoResult.build(400, "用户名或密码错误");
			
		}
		String token = UUID.randomUUID().toString();
		
		user.setPassword(null);
		
		jdeisClient.set(REDIS_USER_KEY_SESSION+":"+token, JsonUtils.objectToJson(user));
		
		jdeisClient.expire(REDIS_USER_KEY_SESSION+":"+token, SSO_SESSION_EXPIRE);
		
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		
		String json = jdeisClient.get(REDIS_USER_KEY_SESSION+":"+token);
		
		//重置用户过期时间
		if (StringUtils.isBlank(json)) {
			
			return TaotaoResult.build(400, "用户已过期请重新登录");
			
		}
		
		jdeisClient.expire(REDIS_USER_KEY_SESSION+":"+token, SSO_SESSION_EXPIRE);
		
		
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
