package com.danbro.springCircularReference.Bean;

/**
 * @Classname H
 * @Description TODO
 *
 * 循环引用之 G 注入 H 是 setter 注入， H 注入 G 是构造器注入
 *
 * @Date 2020/9/14 14:26
 * @Author Danrbo
 */
public class H {
	public G g;

	public H(G g) {
		this.g = g;
	}
}
