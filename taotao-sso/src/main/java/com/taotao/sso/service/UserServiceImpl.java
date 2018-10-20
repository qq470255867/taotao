package com.taotao.sso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper userMapper;

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

}
