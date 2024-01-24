package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	
	@GetMapping("/usedboard")
	public String useddboard() {
		return "board/used/usedboard";
	}
	
	@GetMapping("/sns")
	public String sns() {
		return "board/sns/sns";
	}
	

	
	
	@GetMapping("/insertwalkcheckboard")
	public String insertwalkcheckboard() {
		return "board/walking/insertwalkcheckboard";
	}
}
