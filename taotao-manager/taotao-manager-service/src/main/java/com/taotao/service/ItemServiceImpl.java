package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbitemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = tbitemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbitemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem tbItem, String desc, String itemParam) throws Exception {
		// TODO Auto-generated method stub
		// Item 补全
		// 生成商品ID
		Long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbitemMapper.insert(tbItem);

		TaotaoResult taotaoResult = insertItemDesc(itemId, desc);
		if (taotaoResult.getStatus() != 200) {
			throw new Exception();

		}
		taotaoResult = insertItemParamItem(itemId, itemParam);
		if (taotaoResult.getStatus() != 200) {
			throw new Exception();

		}
		return TaotaoResult.ok();
	}

	public TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();

	}

	// 添加规格参数
	private TaotaoResult insertItemParamItem(long itemId, String itemParam) {
		// 创建一个pojo
		TbItemParamItem itemParaItem = new TbItemParamItem();
		itemParaItem.setItemId(itemId);
		itemParaItem.setParamData(itemParam);
		itemParaItem.setCreated(new Date());
		itemParaItem.setUpdated(new Date());
		// 表中插入数据
		tbItemParamItemMapper.insert(itemParaItem);
		return TaotaoResult.ok();

	}
}
