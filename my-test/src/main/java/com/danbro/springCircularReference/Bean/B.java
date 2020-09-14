package com.danbro.springCircularReference.Bean;

/**
 * @Classname User
 * @Description TODO
 *
 * 循环依赖之 A、B 都是 setter 注入
 *
 * @Date 2020/9/11 11:19
 * @Author Danrbo
 */
public class B {
	public A objA;

	public void setObjA(A objA) {
		this.objA = objA;
	}

	public B() {
		System.out.println("B 构造器");
	}

}
