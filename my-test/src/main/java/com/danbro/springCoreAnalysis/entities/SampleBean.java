package com.danbro.springCoreAnalysis.entities;

/**
 * @Classname SampleBean
 * @Description TODO
 * @Date 2020/5/28 22:29
 * @Author Danrbo
 */
public class SampleBean {
	private int id;

	@Override
	public String toString() {
		return "SampleBean{" +
				"id=" + id +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println("注入属性【id】");
		this.id = id;
	}
}
