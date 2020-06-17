package com.danbro.springAopAnalysis.advices;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Classname LogResultAdvice
 * @Description TODO
 * @Date 2020/6/8 11:11
 * @Author Danrbo
 */
public class LogResultAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println(method.getName() + "方法返回：" + returnValue);
	}
}