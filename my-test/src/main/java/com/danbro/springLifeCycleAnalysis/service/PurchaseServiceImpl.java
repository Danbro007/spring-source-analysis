package com.danbro.springLifeCycleAnalysis.service;

/**
 * @Classname PurchaseServiceImpl
 * @Description TODO
 * @Date 2020/6/5 14:39
 * @Author Danrbo
 */
//@Component
public class PurchaseServiceImpl implements PurchaseService {
	@Override
	public void buy() {
		System.out.println("this is PurchaseServiceImpl buy");
	}
}
