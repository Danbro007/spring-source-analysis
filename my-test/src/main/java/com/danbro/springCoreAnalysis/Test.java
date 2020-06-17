package com.danbro.springCoreAnalysis;

import com.danbro.springCoreAnalysis.entities.SampleBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/6/9 19:52
 * @Author Danrbo
 */
public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("springCoreAnalysis.xml");
		SampleBean sampleBean = applicationContext.getBean(SampleBean.class);
		System.out.println(sampleBean);
	}
}
