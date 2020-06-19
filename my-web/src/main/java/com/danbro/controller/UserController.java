package com.danbro.controller;

import com.danbro.annotations.Danbro;
import com.danbro.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2020/6/15 10:17
 * @Author Danrbo
 */
@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String getUser(@PathVariable("id") int id){
		System.out.println("userId:" + id);
		return "user";
	}
	@GetMapping("/user")
	public String getUser(@RequestParam("name") String name){
		System.out.println("this is @RequestParam name:" + name);
		return "user";
	}
	@ResponseBody
	@PostMapping(value = "/user",produces={"application/json; charset=UTF-8"})
	public User insertUser(@RequestBody User user){
		System.out.println("Insert User:" + user);
		return user;
	}

	@GetMapping("/test")
	public String test(@Danbro("username") String username){
		System.out.println(username);
		return "user";
	}
}
