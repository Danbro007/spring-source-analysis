package com.danbro.springCoreAnalysis;

import com.danbro.springCoreAnalysis.service.IndexService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/5/28 22:29
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");
		IndexService indexService = applicationContext.getBean(IndexService.class);
		System.out.println(indexService);
		applicationContext.registerShutdownHook();
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
//		applicationContext
//		IndexService indexService = applicationContext.getBean(IndexService.class);
//		indexService.testAop1();
//		indexService.testAop2();
	}
}
