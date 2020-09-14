package com.danbro.springCircularReference.Bean;

/**
 * @Classname D
 * @Description TODO
 *
 * 循环依赖之 C、D 都是构造器注入
 *
 * @Date 2020/9/14 14:05
 * @Author Danrbo
 */
public class D {
	public C c;

	public D(C c) {
		this.c = c;
	}
}
