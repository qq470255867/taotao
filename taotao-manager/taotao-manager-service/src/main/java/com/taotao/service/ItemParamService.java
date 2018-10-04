package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	TaotaoResult getItemParamByCid(long cid);
	TaotaoResult insertItemParam(TbItemParam tbItemParam);
	TbItemParam getItemParamById(long id);

}
