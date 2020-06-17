package com.danbro.springAopAnalysis.service;

import com.danbro.springAopAnalysis.entities.Order;
import org.springframework.stereotype.Component;

/**
 * @Classname OrderServiceImpl
 * @Description TODO
 * @Date 2020/6/8 11:09
 * @Author Danrbo
 */
public class OrderServiceImpl implements OrderService {

	@Override
	public Order createOrder(String username, String product) {
		System.out.println("创建订单");
		Order order = new Order();
		order.setUsername(username);
		order.setProduct(product);
		return order;
	}

	@Override
	public Order queryOrder(String username) {
		Order order = new Order();
		order.setUsername("test");
		order.setProduct("test");
		return order;
	}
}