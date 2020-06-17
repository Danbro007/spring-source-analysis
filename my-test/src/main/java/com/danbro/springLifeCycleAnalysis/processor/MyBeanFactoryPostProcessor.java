package com.danbro.springLifeCycleAnalysis.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname MyBeanFactoryPostProcessor
 * @Description TODO
 * @Date 2020/6/8 22:02
 * @Author Danrbo
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	public MyBeanFactoryPostProcessor() {
		super();
		System.out.println("BeanFactoryPostProcessor的实现类构造器");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor的postProcessBeanFactory()方法");
	}
}
