package com.taotao.portal.service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
	
	public ItemInfo getItemInfo(long id);
	
	public TbItemDesc geTbItemDesc(long id);
	
	public TbItemParamItem getItemParam(Long id);

}
