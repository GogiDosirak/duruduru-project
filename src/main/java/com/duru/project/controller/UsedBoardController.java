package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.UsedBoard;
import com.duru.project.service.UsedBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsedBoardController {
	
	@Autowired
	UsedBoardService usedBoardService;
	
	
	@GetMapping("/usedboard")
	public String getProductList(HttpSession session, @PageableDefault(size=8, sort="usboSeq", direction = Direction.DESC) Pageable pageable) {
		Page<UsedBoard> usedBoardList = usedBoardService.usedBoardList(pageable);
		session.setAttribute("usedBoardList", usedBoardList);
		return "board/used/usedBoardList";
	}

	
	

	@GetMapping("insertUsedBoard")
	public String insertUsedBoard() {
		return "board/used/insertUsedBoard";
	}
	

	
	
	@PostMapping("/insertUsedBoard")
	public String insertUsedBoard(UsedBoard usedBoard, MultipartFile file) throws Exception {
		
		usedBoardService.insertUsedBoard(usedBoard, file);
		return "redirect:/usedBoardList";
	}

}
