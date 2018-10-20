package com.taotao.portal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
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
		String result = HttpClientUtil.doGet(REST_BASE_URL + "/itemDesc/" + id);

		TaotaoResult formatToPojo = TaotaoResult.formatToPojo(result, TbItemDesc.class);

		TbItemDesc data = (TbItemDesc) formatToPojo.getData();

		return data;
	}

	@Override
	public TbItemParamItem getItemParam(Long itemId) {

		String json = HttpClientUtil.doGet(REST_BASE_URL + "/itemParam/" + itemId);

		TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);

		TbItemParamItem itemParam = (TbItemParamItem) result.getData();
		
		try {
			
			List<Map> jsonList = JsonUtils.jsonToList(itemParam.getParamData(), Map.class);
			
			StringBuffer sb = new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("    <tbody>\n");
			for (Map m1 : jsonList) {
				sb.append("        <tr>\n");
				sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
				sb.append("        </tr>\n");
				List<Map> list2 = (List<Map>) m1.get("params");
				for (Map m2 : list2) {
					sb.append("        <tr>\n");
					sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
					sb.append("            <td>" + m2.get("v") + "</td>\n");
					sb.append("        </tr>\n");
				}
			}
			sb.append("    </tbody>\n");
			sb.append("</table>");
			
			itemParam.setParamData(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemParam;

	}
}
