package com.danbro.controller;

import com.danbro.data.UserMap;
import com.danbro.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2020/6/15 10:17
 * @Author Danrbo
 */
@Controller
public class UserController {
	@Autowired
	private UserMap userMap;


	@GetMapping("/user/{id}")
	public String getUser(@PathVariable("id") int id) throws Exception {
		User user = (User) userMap.get(id);
		if (user != null){
			System.out.println(user);
			return "user";
		}
		throw new Exception("没有此用户");
	}

	@PostMapping("/user")
	public String insertUser(@RequestBody User user) {
		userMap.put(user.getId(), user);
		return "success";
	}

	@GetMapping("/user")
	public String insertUserByString(@RequestParam("user") User user) {
		userMap.put(user.getId(), user);
		return "success";
	}

	@InitBinder()
	public void initUserBinder(WebDataBinder binder) {
		binder.setDisallowedFields("username");
	}

	@GetMapping
	public String userIndex(){
		return "index";
	}

}
