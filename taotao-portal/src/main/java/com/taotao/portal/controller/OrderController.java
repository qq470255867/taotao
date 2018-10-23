package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	CartService cartService;

	@RequestMapping("/order-cart")
	String showOrderCart(HttpServletResponse response, HttpServletRequest request, Model model) {
		// 取购物车商品列表
		List<CartItem> list = cartService.showItemList(request, response);
		// 传递给页面
		model.addAttribute("cartList", list);

		return "order-cart";
	}

}
