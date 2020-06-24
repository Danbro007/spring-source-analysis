package com.danbro.controller;

import com.danbro.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2020/6/23 16:30
 * @Author Danrbo
 */
@Controller
public class TestController {
	/**
	 * 测试 InternalResourceViewResolver 解析器来解析 JSP
	 */
	@GetMapping("/test1")
	public String getUser(@RequestParam("name") String name) {
		System.out.println("this is @RequestParam name:" + name);
		return "user";
	}

	/**
	 * 测试返回
	 */
	@GetMapping("/test2")
	public ModelAndView test(@RequestParam("username") String username,
							 @RequestParam("password") String password) {
		ModelAndView view = new ModelAndView("test");
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		user.setId(1);
		view.addObject("user",user);
		return view;
	}

	/**
	 * 测试 Restful 风格
	 */
	@ResponseBody
	@GetMapping("/test3")
	public User test2(@RequestParam("username") String username,
					  @RequestParam("password") String password) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		user.setId(1);
		return user;
	}

	/**
	 * 测试 ThymeleafViewResolver 视图解析器
	 */
	@GetMapping("/test4")
	public String getUserFtl(@RequestParam("name") String name, Model model) {
		System.out.println("this is @RequestParamFTL name:" + name);
		User user = new User();
		user.setId(1);
		user.setUsername(name);
		user.setPassword("123456");
		model.addAttribute("user",user);
		return "test";
	}








}
