package com.danbro.springLifeCycleAnalysis.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Classname MyBeanProcessor
 * @Description TODO
 * @Date 2020/6/8 21:58
 * @Author Danrbo
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor() {
		super();
		System.out.println("BeanPostProcessor实现类构造器");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("sampleBean".equals(beanName)) {
			System.out.println("BeanPostProcessor的postProcessBeforeInitialization");
		}
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("sampleBean".equals(beanName)) {
			System.out.println("BeanPostProcessor的postProcessAfterInitialization");
		}
		return null;
	}
}
