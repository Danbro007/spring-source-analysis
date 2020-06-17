package com.danbro.springAopAnalysis;

import com.danbro.springAopAnalysis.service.OrderService;
import com.danbro.springAopAnalysis.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test2
 * @Description TODO
 * @Date 2020/6/8 12:05
 * @Author Danrbo
 */
	public class Test2 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_2_0.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		OrderService orderService = applicationContext.getBean(OrderService.class);
		userService.createUser("danbro","shan",30);
		orderService.createOrder("danbro","phone");
	}
}
