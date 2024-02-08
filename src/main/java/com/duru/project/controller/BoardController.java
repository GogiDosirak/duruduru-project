package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	



	
	@GetMapping("/insertwalkcheckboard")
	public String insertwalkcheckboard() {
		return "board/walking/insertwalkcheckboard";
	}
}
