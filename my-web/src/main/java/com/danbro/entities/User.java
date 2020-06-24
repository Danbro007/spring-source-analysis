package com.danbro.entities;

import com.sun.istack.internal.NotNull;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/6/16 19:47
 * @Author Danrbo
 */
public class User {
	private int id;

	private String username;
	private String password;

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User() {
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User{" +
				"userId=" + id +
				", userName='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
