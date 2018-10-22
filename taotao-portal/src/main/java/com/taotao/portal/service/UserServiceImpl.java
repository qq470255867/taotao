package com.taotao.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
@Service
public class UserServiceImpl implements UserService {
	
	
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			
			String http = HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			
			TaotaoResult pojo = TaotaoResult.formatToPojo(http, TbUser.class);
			if (pojo.getStatus()==200) {
				
				TbUser data = (TbUser) pojo.getData();
				
				return data;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
