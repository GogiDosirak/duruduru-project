package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
	
	@GetMapping("/mall")
	public String mall() {
		return "shop/mall/mall";
	}
	
	@GetMapping("/basket")
	public String basket() {
		return "shop/basket/basket";
	}
	

}
