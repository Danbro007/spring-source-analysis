package com.danbro.controller;

import com.danbro.entities.Book;
import com.danbro.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Classname BookController
 * @Description TODO
 * @Date 2020/6/16 11:01
 * @Author Danrbo
 */
@Controller
public class BookController {
	@RequestMapping("/book")
	public String getBook(@RequestParam("author")String author,
						  Map<String, Object> bookMap,
						  @ModelAttribute("book") Book book,
						  @ModelAttribute("user")User user){
		System.out.println("author:"+author);
		System.out.println("bookMap:" + bookMap);
		System.out.println("haha:"+book);
		return "book";
	}
}
