package com.duru.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HostoreController {
	
	@GetMapping("/hospital")
	public String hospital() {
		return "hostore/hospital/hospital";
	}
	
	@GetMapping("/store")
	public String store() {
		return "hostore/store/store";
	}

}
