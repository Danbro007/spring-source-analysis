package com.danbro.springAopAnalysis.service;

import com.danbro.springAopAnalysis.entities.User;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/6/8 11:05
 * @Author Danrbo
 */
public interface UserService {

	User createUser(String firstName, String lastName, int age);

	User queryUser();
}