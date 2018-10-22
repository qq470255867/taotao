package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;

public interface UserService {
	
	TaotaoResult registerCheck(String param,Integer type);
	
	TaotaoResult createUser(TbUser user);
	
	TaotaoResult UserLogin(String username, String pwd,HttpServletRequest request,HttpServletResponse response);
	
    TaotaoResult getUserByToken(String token);
    
    TaotaoResult UserLogout(String token,HttpServletRequest request,HttpServletResponse response);
}
