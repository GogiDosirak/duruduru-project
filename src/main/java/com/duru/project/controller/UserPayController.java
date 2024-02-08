package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.duru.project.domain.User;
import com.duru.project.domain.UserOrder;
import com.duru.project.domain.UserPay;
import com.duru.project.service.UserPayService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserPayController {
	@Autowired
	private UserPayService userPayService;
	
	
	@GetMapping("/orderHistory")
	public String orderHistory(HttpSession session) {
		User user = (User) session.getAttribute("principal");
		List<UserPay> orderList = userPayService.getUserPayList(user.getUserSeq());
		session.setAttribute("orderList", orderList);
		return "shop/order/orderHistory";
	}
}
