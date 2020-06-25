package com.danbro.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname TestController 实现了 Controller 接口的控制器
 * @Description TODO
 * @Date 2020/6/17 13:37
 * @Author Danrbo
 */
@Component("/implcontroller")
public class ImplController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("this is TestController");
		ModelAndView modelAndView = new ModelAndView("test");
		return modelAndView;
	}
}
