package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.EventBoard;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.EventBoardService;

import jakarta.servlet.http.HttpSession;
import retrofit2.http.GET;

@Controller
public class EventBoardController {
	
	
	@Autowired
	EventBoardService eventBoardService;
	
	
	

	@GetMapping("/event")
	public String freeBoardList(Model model, @PageableDefault(size = 4, sort = "eventSeq", direction = Direction.DESC) Pageable pageable, String searchKeyword) {
		Page<EventBoard> list = null;
		
		if(searchKeyword == null) {
			list = eventBoardService.eventBoardList(pageable);
		} else {
			list = eventBoardService.eventTitleSearchList(searchKeyword, pageable);
		}
		
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("getEventBoardList", list);
		
		return "cs/event/eventList";
	}	
	
	
	
	@GetMapping("/insertEvent")
	public String insertEvent () {
		return "cs/event/insertEvent";
	}
	
	
	@PostMapping("/insertEvent")
	public @ResponseBody ResponseDTO<?> insertEvent (@RequestBody EventBoard eventBoard, HttpSession session) {
		User user = (User)session.getAttribute("principal");
		eventBoard.setUser(user);
		
		eventBoardService.insertEvent(eventBoard);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "글이 등록되었습니다.");
	}
	
	
	
	@GetMapping("/getEventBoard/{eventSeq}")
	public String getEventBoard(@PathVariable int eventSeq, Model model) {
		
		EventBoard findEventBoard = eventBoardService.getEventBoard(eventSeq);
		model.addAttribute("findEventBoard", findEventBoard);
		
		eventBoardService.increaceEventCnt(findEventBoard);
		
		return "cs/event/getEventBoard";
	}
	
	
	
	
	@GetMapping("/updateEventBoard/{eventSeq}")
	public String updateEventBoard(@PathVariable int eventSeq, Model model) {
		EventBoard findEventBoard = eventBoardService.getEventBoard(eventSeq);
		model.addAttribute("findEventBoard", findEventBoard);
		
		
		return "cs/event/updateEvent";
	}
	
	
	
	
	@PutMapping("/updateEventBoard/{eventSeq}")
	public @ResponseBody ResponseDTO<?> updateEventBoard(@PathVariable int eventSeq, Model model, @RequestBody EventBoard eventBoard) {
		
		System.out.println("글 수정 " + eventSeq);
		
		
		EventBoard findEventBoard = eventBoardService.getEventBoard(eventSeq);
		model.addAttribute("findEventBoard", findEventBoard);
		
		findEventBoard.setEventTitle(eventBoard.getEventTitle());
		findEventBoard.setEventContent(eventBoard.getEventContent());
		
		eventBoardService.insertEvent(findEventBoard);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 수정이 완료되었습니다.");
	}
	
	
	
	@DeleteMapping("/deleteEventBoard/{eventSeq}")
	public @ResponseBody ResponseDTO<?> deleteEventBoard(@PathVariable int eventSeq) {
		eventBoardService.deleteEvent(eventSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "삭제가 완료되었습니다.");
	}

}
