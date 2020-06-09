package com.danbro.springCoreAnalysis.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Classname FactoryBeanServiceImpl
 * @Description TODO
 * @Date 2020/6/1 10:34git
 * @Author Danrbo
 */
public class IndexService implements ApplicationContextAware, BeanNameAware, BeanFactoryAware, InitializingBean, BeanClassLoaderAware,DisposableBean {
//	@Autowired
//	UserService userService;
	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("注入属性[name]");
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		System.out.println("IndexService 无参构造器");
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
}


