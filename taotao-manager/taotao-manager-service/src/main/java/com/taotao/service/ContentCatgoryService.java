package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {

	List<EUTreeNode> getContentCatgory(long parentId);

	TaotaoResult insertContentCatgory(long parentId, String name);

}
