package com.danbro;

import com.danbro.service.FactoryBeanService;
import com.danbro.service.MessageService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/5/28 22:29
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageService messageService = applicationContext.getBean(MessageService.class);
		String message = messageService.getMessage();
		System.out.println(message);
	}
}
