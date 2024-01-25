package com.duru.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Basket;
import com.duru.project.domain.Product;
import com.duru.project.domain.User;
import com.duru.project.service.BasketService;
import com.duru.project.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BasketController {
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/basket")
	public String basket() {
		return "shop/basket/basket";
	}
	
	@PostMapping("/insertBasket/{userSeq}")
	public String insertBasket(@PathVariable int userSeq,int productSeq, int productAmount, HttpSession session) {
		Basket basket = new Basket();
		User user = (User)session.getAttribute("principal"); 
		Product product = productService.getProduct(productSeq);
		basket.setUser(user);
		basket.setProduct(product);
		basket.setBasketPrice(productAmount * basket.getProduct().getProductPrice());
		basket.setBasketProductAmount(productAmount);
		basketService.insertBasket(basket);
		return "redirect:/basket";
	}

}
