package com.danbro.springAopAnalysis;

import com.danbro.springAopAnalysis.service.OrderService;
import com.danbro.springAopAnalysis.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/6/8 11:19
 * @Author Danrbo
 */
public class Test1 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_1_2.xml");
		OrderService orderService = applicationContext.getBean(OrderService.class);
		UserService userService = applicationContext.getBean(UserService.class);
		userService.createUser("Tom", "Cruise", 55);
		userService.queryUser();
		orderService.createOrder("Leo", "随便买点什么");
		orderService.queryOrder("Leo");
	}
}
