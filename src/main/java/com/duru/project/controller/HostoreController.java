package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.duru.project.domain.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HostoreController {
	
	@GetMapping("/hospital")
	public String hospital(HttpSession session ) {
		
		User user = (User) session.getAttribute("principal");

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		
		return "hostore/hospital/hospital";
	}
	
	
}
