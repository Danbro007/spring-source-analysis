package com.danbro.springLifeCycleAnalysis.service;

/**
 * @Classname PurchaseServiceImpl2
 * @Description TODO
 * @Date 2020/6/5 15:25
 * @Author Danrbo
 */
//@Component
public class PurchaseServiceImpl2  implements PurchaseService{
	@Override
	public void buy() {
		System.out.println("this is PurchaseServiceImpl2 buy");
	}
}
