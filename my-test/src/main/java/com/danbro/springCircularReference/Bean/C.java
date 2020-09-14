package com.danbro.springCircularReference.Bean;

/**
 * @Classname C
 * @Description TODO
 *
 * 循环依赖之 C、D 都是构造器注入
 *
 * @Date 2020/9/14 14:05
 * @Author Danrbo
 */
public class C {
	public D d;

	public C(D d) {
		this.d = d;
	}
}
