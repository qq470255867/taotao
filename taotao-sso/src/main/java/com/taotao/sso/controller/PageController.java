package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	

	
	
	@RequestMapping("/showRegister")
	public String showRegister(){
	
		return "register";
		
	}
	@RequestMapping("/login")
	public String showLogin(String redirect,Model model){
		
		model.addAttribute("redirect",redirect);
		
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(String redirect,Model model){
		model.addAttribute("redirect",redirect);
		return "login";
	}

}
