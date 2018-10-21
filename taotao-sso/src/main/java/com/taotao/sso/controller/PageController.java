package com.taotao.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class PageController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/showRegister")
	public String showRegister(){
		
		
		return "register";
		
	}
	@RequestMapping("/showLogin")
	public String showLogin(){
		
		return "login";
	}

}
