package com.danbro.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname MyController
 * @Description TODO
 * @Date 2020/6/11 11:25
 * @Author Danrbo
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(){
		System.out.println("this is login");
		return "login";
	}

	@GetMapping("/logout")
	public String next(){
		return "logout";
	}
}
