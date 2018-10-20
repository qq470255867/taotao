package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.mapper.SelectCatDao;
import com.taotao.rest.pojo.CatName;
@Service
public class CatServiceImpl implements CatService {
	
	@Autowired
	SelectCatDao selectCatDao;

	@Override
	public TaotaoResult SelectCatNameById(long id) {
		
		
		CatName names = selectCatDao.selectByid(id);
		
		return TaotaoResult.ok(names);
		
	}

}
