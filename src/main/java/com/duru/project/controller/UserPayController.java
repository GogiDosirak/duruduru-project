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

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		List<UserPay> orderList = userPayService.getUserPayList(user.getUserSeq());
		session.setAttribute("orderList", orderList);
		return "shop/order/orderHistory";
	}
}
