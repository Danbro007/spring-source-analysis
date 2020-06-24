package com.danbro.advices;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname MyExceptionHandler
 * @Description TODO
 * @Date 2020/6/23 12:40
 * @Author Danrbo
 */
//@ControllerAdvice
public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView view = new ModelAndView("error");
		view.addObject("message",ex.getMessage());
		return view;
	}
}
