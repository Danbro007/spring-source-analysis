package com.danbro.springLifeCycleAnalysis.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Classname MyAspect
 * @Description TODO
 * @Date 2020/6/3 12:16
 * @Author Danrbo
 */

@Component
@Aspect
public class MyAspect {

	@Pointcut("execution(* com.danbro.springLifeCycleAnalysis.service.IndexService.testAop1(..))") // the pointcut expression
	public void applyIndexService1(){
	}
	@Pointcut("execution(* com.danbro.springLifeCycleAnalysis.service.IndexService.testAop2(..))") // the pointcut expression
	public void applyIndexService2(){
	}

	@Before("applyIndexService1()")
	public void beforeAop1(){
		System.out.println("----------this is beforeAop1------");
	}

	@After("applyIndexService1()")
	public void afterAop1(){
		System.out.println("----------this is afterAop1------");
	}

	@After("applyIndexService2()")
	public void afterAop2(){
		System.out.println("----------this is afterAop2------");
	}

	@Before("applyIndexService2()")
	public void beforeAop2(){
		System.out.println("----------this is beforeAop2------");
	}


	@Pointcut("execution(* com.danbro.springLifeCycleAnalysis.service.PurchaseService.*(..))")
	public void purchaseAop(){
	}

	@Before("purchaseAop()")
	public void aop3(){
		System.out.println("----------this is aop3------");
	}

	@Around("purchaseAop()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("method is start");
		pjp.proceed();
		System.out.println("method is end");
	}
}
