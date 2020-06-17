package com.danbro.springCircularReference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/6/9 19:33
 * @Author Danrbo
 */
@Component
public class UserService {
	@Autowired
	private OrderService orderService;

	public UserService() {
		System.out.println("UserService 构造器");
	}
}
