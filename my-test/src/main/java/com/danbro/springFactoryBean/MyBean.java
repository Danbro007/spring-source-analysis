package com.danbro.springFactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Classname CustomTask
 * @Description TODO
 * @Date 2020/6/9 14:58
 * @Author Danrbo
 */
@Component
public class MyBean implements FactoryBean {
	private String message;

	public MyBean(String message) {
		this.message = message;
	}

	public MyBean() {
		System.out.println("构造器");
		this.message = "通过构造方法初始化实例";
	}

	@Override
	public Object getObject() throws Exception {
		System.out.println("getObject");
		return new MyBean("通过FactoryBean.getObject()创建实例");

	}

	@Override
	public Class<?> getObjectType() {
		return MyBean.class;
	}

	public String getMessage() {
		return message;
	}
}
