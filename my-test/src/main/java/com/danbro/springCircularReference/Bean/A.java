package com.danbro.springCircularReference.Bean;

/**
 * @Classname UserInfo
 * @Description TODO
 *
 * 循环依赖之 A、B 都是 setter 注入
 *
 * @Date 2020/9/11 11:20
 * @Author Danrbo
 */
public class A {
	public B objB;

	public void setObjB(B objB) {
		this.objB = objB;
	}
	public A() {
		System.out.println("A 构造器");
	}

}
