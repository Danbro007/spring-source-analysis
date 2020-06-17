package com.danbro.springLifeCycleAnalysis.service;

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