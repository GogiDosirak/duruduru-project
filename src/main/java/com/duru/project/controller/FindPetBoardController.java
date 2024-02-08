package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.duru.project.service.FindPetBoardService;

@Controller
public class FindPetBoardController {

	
	@Autowired
	private FindPetBoardService findPetBoardService;
	
	
}
