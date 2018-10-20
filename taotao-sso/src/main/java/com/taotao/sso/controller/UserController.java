package com.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object registerCheck(@PathVariable String param, @PathVariable Integer type, String callback) {

		TaotaoResult result = null;
		if (StringUtils.isBlank(param)) {

			result = TaotaoResult.build(400, "参数值不能为空");

		}
		if (type == null) {

			result = TaotaoResult.build(400, "参数类型不能为空");

		}
		if (type != 1 && type != 2 && type != 3) {

			result = TaotaoResult.build(400, "参数类型有误");

		}
		if (result != null) {
			if (callback != null) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}

		}
		try {

			result = userService.registerCheck(param, type);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (callback != null) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public TaotaoResult createUser(TbUser user){
		
		try {
			TaotaoResult result = userService.createUser(user);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.build(500, "用户创建失败");
		
	}

}
