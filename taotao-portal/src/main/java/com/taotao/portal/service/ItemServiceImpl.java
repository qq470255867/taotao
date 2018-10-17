package com.taotao.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${ITEM_DESC}")
	private String ITEM_DESC;
	@Value("${ITEM_PARAM}")
	private String ITEM_PARAM;

	@Override
	public ItemInfo getItemInfo(long id) {
		// http://localhost:8081/rest/itemInfo/536563

		String result = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO + id);

		TaotaoResult formatToPojo = TaotaoResult.formatToPojo(result, ItemInfo.class);

		ItemInfo data = (ItemInfo) formatToPojo.getData();

		return data;
	}

	@Override
	public TbItemDesc geTbItemDesc(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbItemParamItem geTbItemParamItem(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
