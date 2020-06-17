package com.danbro.springAopAnalysis.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Classname SystemArchitecture
 * @Description TODO
 * @Date 2020/6/8 11:55
 * @Author Danrbo
 */
@Aspect
public class SystemArchitecture {
	// service 实现，注意这里指的是方法实现，其实通常也可以使用 bean(*ServiceImpl)
	@Pointcut("execution(* com.danbro.springAopAnalysis.service.*.*(..))")
	public void businessService() {
	}
}
