package com.danbro.entities;

/**
 * @Classname Order
 * @Description TODO
 * @Date 2020/6/23 11:54
 * @Author Danrbo
 */
public class Order {

	private int id;
	private String username;

	public Order(int id, String username) {
		this.id = id;
		this.username = username;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
