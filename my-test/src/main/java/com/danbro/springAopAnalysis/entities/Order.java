package com.danbro.springAopAnalysis.entities;

/**
 * @Classname Order
 * @Description TODO
 * @Date 2020/6/8 11:07
 * @Author Danrbo
 */
public class Order {
	private String username;
	private String product;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order{" +
				"username='" + username + '\'' +
				", product='" + product + '\'' +
				'}';
	}
}
