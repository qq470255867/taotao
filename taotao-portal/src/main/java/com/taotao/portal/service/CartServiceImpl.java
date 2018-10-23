package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;

	@Override
	public TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 取商品信息
		CartItem cartItem = null;
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			// 如果存在此商品
			if (cItem.getId() == itemId) {
				// 增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO + itemId);
			// 把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);

		return TaotaoResult.ok();
	}

	/**
	 * 从cookie中取商品列表
	 * <p>
	 * Title: getCartItemList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<CartItem> showItemList(HttpServletRequest request, HttpServletResponse response) {

		List<CartItem> list = getCartItemList(request);

		return list;
	}

	@Override
	public TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {

		List<CartItem> list = getCartItemList(request);

		for (CartItem cartItem : list) {
			if (cartItem.getId() == itemId) {

				list.remove(cartItem);
				break;

			}

		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list));

		return TaotaoResult.ok();
	}
}