package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CSController {
	
	

	
	
	@GetMapping("/event")
	public String event() {
		return "cs/event/event";
	}

}
