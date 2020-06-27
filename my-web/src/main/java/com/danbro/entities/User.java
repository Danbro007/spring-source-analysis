package com.danbro.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/6/16 19:47
 * @Author Danrbo
 */
public class User {
	private int id;

	@NotEmpty(message = "用户名不能为空！")
	private String username;

	@Size(min = 8,max = 10, message = "密码不能长度不能低于8位高于10位！")
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
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
