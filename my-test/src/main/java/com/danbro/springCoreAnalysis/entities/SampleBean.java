package com.danbro.springCoreAnalysis.entities;

/**
 * @Classname SampleBean
 * @Description TODO
 * @Date 2020/6/9 19:48
 * @Author Danrbo
 */

public class SampleBean {
	private int id;
	private String name;

	public SampleBean() {
		System.out.println("SampleBean构造器");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SampleBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
