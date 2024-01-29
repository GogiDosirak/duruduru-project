package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Basket;
import com.duru.project.domain.User;
import com.duru.project.domain.UserOrder;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/order/{userSeq}")
	public String order(@PathVariable int userSeq, HttpSession session) { //jsp에서 "order/${principal.userSeq}"로 받아옴
		session.getAttribute("basketList"); //session에서 장바구니 물품 불러와야 물품이 뜸
		return "shop/order/order";
	}
	
	
	@PostMapping("/insertOrder")
	public @ResponseBody ResponseDTO<?> insertOrder(@RequestBody UserOrder userOrder, HttpSession session) {
		User user = (User)session.getAttribute("principal");
		userOrder.setUser(user);
		orderService.insertOrder(userOrder);
		session.setAttribute("userOrder", userOrder);
		return new ResponseDTO<>(HttpStatus.OK.value(), "결제하기 창으로 넘어갑니다.");
		
	}
	
	
	@GetMapping("/pay/{orderSeq}")
	public String pay(@PathVariable int orderSeq) {
		return "shop/order/pay";
	}
	

}
