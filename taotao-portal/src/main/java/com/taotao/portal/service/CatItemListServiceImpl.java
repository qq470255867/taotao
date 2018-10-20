package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CatSelectResult;
import com.taotao.portal.pojo.Item;

@Service
public class CatItemListServiceImpl implements CatItemListService {

	@Override
	public CatSelectResult getCatItemList(long Cid) {

		String http = HttpClientUtil.doGet("http://localhost:8081/rest/selectByCid/" + Cid);

		TaotaoResult formatToList = TaotaoResult.formatToList(http, TbItem.class);
		try {
			
			List<TbItem> tbItemList = (List<TbItem>) formatToList.getData();
			if (!tbItemList.isEmpty()) {
				
				List<Item> itemList = new ArrayList<>();
				
				for (TbItem tbitem : tbItemList) {
					
					Item item = new Item();
					
					item.setId(tbitem.getId());
					item.setCategory_name(null);
					item.setImage(tbitem.getImage());
					item.setItem_desc(tbitem.getSellPoint());
					item.setPrice(tbitem.getPrice());
					item.setSell_point(tbitem.getSellPoint());
					item.setTitle(tbitem.getTitle());
					
					itemList.add(item);
					
				}
				CatSelectResult result = new CatSelectResult();
				
				result.setList(itemList);
				return result;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
