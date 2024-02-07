package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.FreeBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardService freeBoardService;
	
	
	@GetMapping("/freeboard")
	public String freeBoardList(HttpSession session) {
		
		List<FreeBoard> getFreeBoadList = freeBoardService.freeBoardList();
		session.setAttribute("getFreeBoardList", getFreeBoadList);
		return "board/free/freeBoardList";
	}
	
	
	
	@GetMapping("/insertfreeboard")
	public String insertFreeBoard() {
		return "board/free/insertFreeBoard";
	}
	
	
	
	@PostMapping("/insertfreeboard")
	public @ResponseBody ResponseDTO<?> insertFreeBoard(HttpSession session, @RequestBody FreeBoard freeboard) {
		User user = (User) session.getAttribute("principal");
		freeboard.setUser(user);
		
		freeBoardService.insertFreeBoard(freeboard);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 등록이 완료되었습니다.");
	}
	
	
	@GetMapping("/getfreeboard/{frboSeq}")
	public  String getFreeBoard(@PathVariable int frboSeq, HttpSession session) {
		
		FreeBoard findFreeBoard = freeBoardService.getFreeBoard(frboSeq);
		session.setAttribute("findFreeBoard", findFreeBoard);
		
		return "board/free/getFreeBoard";
		
	}
	

}
