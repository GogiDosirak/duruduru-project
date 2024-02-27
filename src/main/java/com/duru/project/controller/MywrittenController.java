package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.duru.project.domain.FindPetBoard;
import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.Inquiry;
import com.duru.project.domain.UsedBoard;
import com.duru.project.domain.User;
import com.duru.project.domain.WalkBoard;
import com.duru.project.domain.WalkCheckBoard;
import com.duru.project.service.FindPetBoardService;
import com.duru.project.service.FreeBoardService;
import com.duru.project.service.InquiryService;
import com.duru.project.service.UsedBoardService;
import com.duru.project.service.WalkBoardService;
import com.duru.project.service.WalkCheckBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MywrittenController {
	@Autowired
	private FindPetBoardService findPetBoardService;
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private UsedBoardService usedBoardService;
	
	@Autowired
	private WalkBoardService walkBoardService;
	
	@Autowired
	private WalkCheckBoardService walkCheckBoardService;
	
	@GetMapping("/mywritten/{userSeq}")
	public String mywritten(@PathVariable int userSeq, Model model,HttpSession session) {
		User user = (User)session.getAttribute("principal");
		userSeq = user.getUserSeq();
		
		List<FindPetBoard> findPetBoard = findPetBoardService.findPetBoard(userSeq);
		List<FreeBoard> freeBoard = freeBoardService.freeBoard(userSeq);
		List<Inquiry> inquiry = inquiryService.inquiry(userSeq);
		List<UsedBoard> usedBoard = usedBoardService.usedBoard(userSeq);
		List<WalkBoard> walkBoard = walkBoardService.walkBoard(userSeq);
		List<WalkCheckBoard> walkCheckBoard = walkCheckBoardService.walkCheckBoard(userSeq);
		
		
		model.addAttribute("findPetBoard", findPetBoard);
		model.addAttribute("freeBoard", freeBoard);
		model.addAttribute("inquiry", inquiry);
		model.addAttribute("usedBoard", usedBoard);
		model.addAttribute("walkBoard", walkBoard);
		model.addAttribute("walkCheckBoard",walkCheckBoard);
		
		return "/user/mywritten";
	}



}
