package com.danbro.springFactoryBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/6/9 15:06
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MyBean myBean1 = (MyBean) applicationContext.getBean("myBean");
		System.out.println("myBean1 = " + myBean1.getMessage());
		//返回工厂本身的实例
		MyBean myBean2 = (MyBean) applicationContext.getBean("&myBean");
		System.out.println("myBean2 = " + myBean2.getMessage());
		System.out.println("myBean1.equals(myBean2) = " + myBean1.equals(myBean2));
	}
}
