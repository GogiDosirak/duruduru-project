package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CSController {
	
	
	@GetMapping("/notice")
	public String notice() {
		return "cs/notice/notice";
	}
	
	@GetMapping("/onetoone")
	public String onetoone() {
		return "cs/onetoone/onetoone";
	}
	
	@GetMapping("/event")
	public String event() {
		return "cs/event/event";
	}

}
