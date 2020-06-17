package com.danbro.springAopAnalysis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

/**
 * @Classname LogArgsAspect
 * @Description TODO
 * @Date 2020/6/8 11:58
 * @Author Danrbo
 */

@Aspect
public class LogArgsAspect {
	@Before("com.danbro.springAopAnalysis.aspects.SystemArchitecture.businessService()")
	public void logArgs(JoinPoint joinPoint){
		System.out.println("方法执行前，打印入参："+ Arrays.toString(joinPoint.getArgs()));
	}
}

