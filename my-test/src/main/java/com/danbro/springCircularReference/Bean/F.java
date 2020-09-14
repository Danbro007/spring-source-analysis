package com.danbro.springCircularReference.Bean;

/**
 * @Classname F
 * @Description TODO
 *
 * 循环引用之 E 注入 F 是构造器注入， F 注入 E 是 Setter 注入
 *
 * @Date 2020/9/14 14:21
 * @Author Danrbo
 */
public class F {
	public E e;

	public void setE(E e) {
		this.e = e;
	}
}
