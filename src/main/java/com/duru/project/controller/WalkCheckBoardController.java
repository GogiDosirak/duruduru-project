package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.User;
import com.duru.project.domain.WalkCheckBoard;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.WalkCheckBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WalkCheckBoardController {
	
	@Autowired
	private WalkCheckBoardService walkCheckBoardService;
	
	@PostMapping("/insertwachbo")
	public @ResponseBody ResponseDTO<?> insertwachbo(@RequestBody WalkCheckBoard walkCheckBoard, HttpSession session) {
		User finduser = (User)session.getAttribute("principal");
		walkCheckBoard.setUser(finduser);
		walkCheckBoardService.insertUser(walkCheckBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 등록이 완료되었습니다.");
	}
	
	@GetMapping("/walkcheckboard")
	public String getPostList(HttpSession session, @PageableDefault(size = 4, sort ="wachboSeq" , direction = Sort.Direction.DESC) Pageable pageable) {
		
		session.setAttribute("getwachList", walkCheckBoardService.getwachList(pageable));
		return "board/walking/walkcheckboard";
	}
	
	
	
	

}
