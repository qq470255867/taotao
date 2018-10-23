package com.taotao.order.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	TbOrderMapper orderMapper;
	@Autowired
	TbOrderItemMapper orderItemMapper;
	@Autowired
	TbOrderShippingMapper shippingMapper;
	@Autowired
	JedisClient jedis;

	@Value("${ORDER_GEN_KEY}")
	String ORDER_GEN_KEY;

	@Value("${ORDER_INIT_ID}")
	String ORDER_INIT_ID;

	@Value("${ORDER_DETAIL_GEN_KEY}")
	String ORDER_DETAIL_GEN_KEY;

	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping shipping) {

		// 插入订单表
		String string = jedis.get(ORDER_GEN_KEY);

		if (StringUtils.isBlank(string)) {

			jedis.set(ORDER_GEN_KEY, ORDER_INIT_ID);

		}
		long orderId = jedis.incr(ORDER_GEN_KEY);

		order.setOrderId(orderId + "");
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		// 是否评价
		order.setBuyerRate(0);

		orderMapper.insert(order);
		// 插入订单明细表
		for (TbOrderItem tbOrderItem : list) {

			tbOrderItem.setId(jedis.incr(ORDER_DETAIL_GEN_KEY) + "");
			tbOrderItem.setOrderId(orderId+"");

			orderItemMapper.insert(tbOrderItem);

		}
		// 插入物流表
		shipping.setCreated(new Date());
		shipping.setUpdated(new Date());
		shipping.setOrderId(orderId + "");

		shippingMapper.insert(shipping);
		return TaotaoResult.ok(orderId);
	}

}
