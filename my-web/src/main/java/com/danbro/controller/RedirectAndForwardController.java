package com.danbro.controller;

import com.danbro.entities.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname RedirectController 测试重定向和转发请求
 * @Description TODO
 * @Date 2020/6/23 11:52
 * @Author Danrbo
 */
@Controller
public class RedirectAndForwardController {

	private static Map<Integer,Order> orderMap = new HashMap<>(16);

	static {
		orderMap.put(1,new Order(1,"John"));
		orderMap.put(2,new Order(2,"Li"));
		orderMap.put(3,new Order(3,"Shan"));
		orderMap.put(4,new Order(4,"Alex"));
	}

	@GetMapping("/{type}/order/{id}")
	public String getOrder(@PathVariable("type") String type,
						   @PathVariable("id") int id,
						   Model model) {
		Order order = orderMap.get(id);
		if ("redirect".equals(type)) {
			return "redirect:/redirect";
		} else if ("forward".equals(type)) {
			model.addAttribute("order", order);
			return "forward:/forward";
		}
		else {
			return "include:/include";
		}
	}

	@GetMapping("/redirect")
	public String redirect(Model model){
		model.addAttribute("type","重定向");
		return "orderInfo";
	}
	@GetMapping("/forward")
	public String forward(Model model){
		model.addAttribute("type","转发");
		return "orderInfo";
	}

	@GetMapping("/order/{id}")
	public String forward(@PathVariable("id") int id) throws Exception {
		Order order = orderMap.get(id);
		if (order == null){
			throw new Exception("找不到订单号");
		}
		return "orderInfo";
	}

}
