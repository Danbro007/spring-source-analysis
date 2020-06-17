package com.danbro.springAopAnalysis.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Classname LogResultAspect
 * @Description TODO
 * @Date 2020/6/8 12:02
 * @Author Danrbo
 */
@Aspect
public class LogResultAspect {
	@AfterReturning(value = "com.danbro.springAopAnalysis.aspects.SystemArchitecture.businessService()",returning = "result")
	public void logResult(Object result){
		System.out.println(result);
	}
}
