package com.danbro.springAopAnalysis.service;

import com.danbro.springAopAnalysis.entities.User;
import org.springframework.stereotype.Component;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2020/6/8 11:10
 * @Author Danrbo
 */
public class UserServiceImpl implements UserService {

	@Override
	public User createUser(String firstName, String lastName, int age) {
		System.out.println("创建用户");
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);
		return user;
	}

	@Override
	public User queryUser() {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setAge(20);
		return user;
	}
}