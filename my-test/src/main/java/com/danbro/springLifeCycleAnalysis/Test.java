package com.danbro.springLifeCycleAnalysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/5/28 22:29
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("容器开始初始化");
		System.out.println("----------------------------");
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");
		System.out.println("----------------------------");
		System.out.println("容器初始化成功");
		System.out.println("----------------------------");
		System.out.println("现在关闭容器");
		applicationContext.registerShutdownHook();
		System.out.println("----------------------------");
	}
}
