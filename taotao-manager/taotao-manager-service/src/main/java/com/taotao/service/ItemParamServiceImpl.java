package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;

//商品的规格参数模板
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		com.taotao.pojo.TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();// 创建查询条件
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));

		}
		// TODO Auto-generated method stub
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
		// 补全pojo
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		// 插入到规格参数模板表中
		itemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();

	}

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		// 查询商品列表
		TbItemParamExample example = new TbItemParamExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
