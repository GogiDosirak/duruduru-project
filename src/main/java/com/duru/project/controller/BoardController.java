package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	
	
	
	@GetMapping("/sns")
	public String sns() {
		return "board/sns/sns";
	}
	
	@GetMapping("/walking")
	public String walking() {
		return "board/walking/walking";
	}
	
	
	@GetMapping("/insertwalkcheckboard")
	public String insertwalkcheckboard() {
		return "board/walking/insertwalkcheckboard";
	}
}
