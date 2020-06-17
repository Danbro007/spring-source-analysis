package com.danbro.springLifeCycleAnalysis.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * @Classname MyInstantiationAwareBeanPostProcessor
 * @Description TODO
 * @Date 2020/6/4 15:00
 * @Author Danrbo
 */
@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {


	public MyInstantiationAwareBeanPostProcessor() {
		super();
		System.out.println("InstantiationAwareBeanPostProcessorAdapter实现类构造器");
	}

	/**
	 * 实例化 bean 之前调用
	 * @param beanClass
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if ("sampleBean".equals(beanName)){
			System.out.println("InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation()方法 ");
		}
		return null;
	}

	/**
	 * 实例化 bean 之后调用
	 * 可以对指定的bean返回 false，返回 false 表示不对这个bean进行属性注入。
	 * @param bean the bean instance created, with properties not having been set yet
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if ("sampleBean".equals(beanName)){
			System.out.println("InstantiationAwareBeanPostProcessor的postProcessAfterInstantiation()方法 ");
		}
		return true;
	}

	/**
	 * 处理类中的某个属性，并为这个属性赋值
	 * @param pvs the property values that the factory is about to apply (never {@code null})
	 * @param bean the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if ("sampleBean".equals(beanName)){
			System.out.println("InstantiationAwareBeanPostProcessor的postProcessProperties()方法 ");
		}
		return null;
	}
}
