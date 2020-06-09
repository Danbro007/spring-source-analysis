package com.danbro.springCoreAnalysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname MessageServiceImpl
 * @Description TODO
 * @Date 2020/6/1 13:27
 * @Author Danrbo
 */
//@Component
public class UserService {
//	@Autowired
//	IndexService indexService;

	public String getMessage() {
		return "hello world";
	}

	public UserService() {
		System.out.println("UserService Constructor");
	}
}