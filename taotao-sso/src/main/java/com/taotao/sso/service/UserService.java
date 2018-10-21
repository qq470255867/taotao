package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	
	TaotaoResult registerCheck(String param,Integer type);
	
	TaotaoResult createUser(TbUser user);
	
	TaotaoResult UserLogin(String username, String pwd);
	
    TaotaoResult getUserByToken(String token);
}
