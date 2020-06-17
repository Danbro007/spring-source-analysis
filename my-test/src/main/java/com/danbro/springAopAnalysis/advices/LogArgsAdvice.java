package com.danbro.springAopAnalysis.advices;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Classname LogArgsAdvice
 * @Description TODO
 * @Date 2020/6/8 11:11
 * @Author Danrbo
 */
public class LogArgsAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("准备执行方法: " + method.getName() + ", 参数列表：" + Arrays.toString(args));
	}
}