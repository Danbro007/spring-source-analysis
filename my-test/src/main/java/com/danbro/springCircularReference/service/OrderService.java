package com.danbro.springCircularReference.service;

import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname IndexService
 * @Description TODO
 * @Date 2020/6/9 19:32
 * @Author Danrbo
 */
@Component
public class OrderService {
	@Autowired
	private UserService userService;

	public OrderService() {
		System.out.println("OrderService 构造器");
	}
}
