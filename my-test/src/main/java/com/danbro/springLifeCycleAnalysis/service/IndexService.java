package com.danbro.springLifeCycleAnalysis.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Classname FactoryBeanServiceImpl
 * @Description TODO
 * @Date 2020/6/1 10:34git
 * @Author Danrbo
 */
public class IndexService implements ApplicationContextAware, BeanNameAware, BeanFactoryAware,
									InitializingBean, BeanClassLoaderAware,DisposableBean, EnvironmentAware,
									ImportAware,ResourceLoaderAware,
									ApplicationEventPublisherAware {
	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {

		System.out.println("注入属性[name]");
		this.name = name;
		System.out.println("bean的属性注入完毕");
		System.out.println("----------------------------");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println("----------------------------");
		System.out.println("开始bean的属性注入");
		System.out.println("注入属性[id]");
		this.id = id;
	}

	public void testAop1() {
		System.out.println("this is IndexService AOP1");
	}
	public void testAop2() {
		System.out.println("this is IndexService AOP2");
	}

	public IndexService() {
		System.out.println("----------------------------");
		System.out.println("IndexService实例化完毕");
		System.out.println("----------------------------");
	}

	@PostConstruct
	public void post(){
		System.out.println("调用@PostConstruct注解的方法");
	}

	@PreDestroy
	public void preDestroy(){
		System.out.println("执行 PreDestroy");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("调用ApplicationContextAware接口的setApplicationContext()方法");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("调用BeanNameAware接口的setBeanName()方法");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用BeanFactoryAware接口的setBeanFactory()方法");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("调用InitializingBean接口的afterPropertiesSet()方法");
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("调用BeanClassLoaderAware接口的setBeanClassLoader()方法");
	}

	@Override
	public void destroy() throws Exception{
		System.out.println("执行 Destroy");
	}

	public void destroyMethod(){
		System.out.println("执行 destroyMethod");
	}

	public void initMethod(){
		System.out.println("执行 initMethod");
	}

	@Override
	public String toString() {
		return "IndexService{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("调用EnvironmentAware接口的setEnvironment()方法");
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("调用ApplicationEventPublisherAware接口的setApplicationEventPublisher()方法");
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("调用ResourceLoaderAware接口的setResourceLoader()方法");

	}

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		System.out.println("调用ImportAware接口的setImportMetadata()方法");
	}
}


