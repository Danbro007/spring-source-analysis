package com.danbro.controller;

import com.danbro.data.UserMap;
import com.danbro.entities.CodeEnum;
import com.danbro.entities.Result;
import com.danbro.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
		if (user != null) {
			System.out.println(user);
			return "user";
		}
		throw new Exception("没有此用户");
	}

	@ResponseBody
	@PostMapping("/user")
	public Result insertUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			return Result.resultOfFailure(CodeEnum.FAILURE,allErrors);
		}
		System.out.println(user);
		userMap.put(user.getId(), user);
		return Result.resultOfSuccess(CodeEnum.SUCCESS);
	}

	@GetMapping("/user")
	public String insertUserByString(@RequestParam("user") User user) {
		userMap.put(user.getId(), user);
		return "success";
	}

	@InitBinder("user")
	public void initUserBinder(WebDataBinder binder) {
		binder.setDisallowedFields("username");
	}

	@GetMapping
	public String userIndex() {
		return "index";
	}

}
