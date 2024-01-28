package com.duru.project.controller;

import java.util.List;

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

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.FreeComment;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.FreeBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardService freeBoardService;
	
	
	
	
//	@GetMapping("/freeboard")
//	public String freeBoardList(Model model, @PageableDefault(size = 4, sort = "frboSeq", direction = Direction.DESC) Pageable pageable, String searchKeyword) {
//		
//		model.addAttribute("getFreeBoardList", freeBoardService.freeBoardList(pageable));
//		return "board/free/freeBoardList";
//	}
	
	
	
	@GetMapping("/freeboard")
	public String freeBoardList(Model model, @PageableDefault(size = 4, sort = "frboSeq", direction = Direction.DESC) Pageable pageable, String searchKeyword) {
		Page<FreeBoard> list = null;

		if (searchKeyword == null) {
			list = freeBoardService.freeBoardList(pageable); // 기존의 리스트보여줌
		} else {
			list = freeBoardService.boardTitleSearchList(searchKeyword, pageable); // 검색리스트반환
		}

	    model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("getFreeBoardList", list);
		return "board/free/freeBoardList";

	}
	
	
//	@GetMapping("/freeboard/search")
//	public String freeBoardSearchList(Model model, @PageableDefault(size = 4, sort = "frboSeq", direction = Direction.DESC) Pageable pageable, String searchKeyword) {
//		Page<FreeBoard> list = null;
//
//		if (searchKeyword == null) {
//			list = freeBoardService.freeBoardList(pageable); // 기존의 리스트보여줌
//		} else {
//			list = freeBoardService.boardTitleSearchList(searchKeyword, pageable); // 검색리스트반환
//		}
//
//	    model.addAttribute("searchKeyword", searchKeyword);
//		model.addAttribute("getFreeSearchList", list);
//		return "board/free/freeBoardSearchList";
//
//	}
	
	
	
	
	
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
	public  String getFreeBoard(@PathVariable int frboSeq, Model model) {
		
		FreeBoard findFreeBoard = freeBoardService.getFreeBoard(frboSeq);
		model.addAttribute("findFreeBoard", findFreeBoard);
		//조회수
		freeBoardService.increaceCnt(findFreeBoard);
		
		
		//댓글
		List<FreeComment> freeCommentList = freeBoardService.FreeCommentList();
		model.addAttribute("freeCommentList", freeCommentList);
		
		
		return "board/free/getFreeBoard";
		
	}
	
	
	@GetMapping("/updateFreeBoard/{frboSeq}")
	public String updateFreeBoard(@PathVariable int frboSeq, Model model) {
		FreeBoard findFreeBoard =freeBoardService.getFreeBoard(frboSeq);
		model.addAttribute("findFreeBoard", findFreeBoard);
		
		return "board/free/updateFreeBoard";
	}
	
	
	
	@PutMapping("/updateFreeBoard/{frboSeq}")
	public @ResponseBody ResponseDTO<?> updateFreeBoard(@PathVariable int frboSeq, Model model, @RequestBody FreeBoard freeboard) {
		FreeBoard findFreeBoard =freeBoardService.getFreeBoard(frboSeq);
		model.addAttribute("findFreeBoard", findFreeBoard);
		
		findFreeBoard.setFrboTitle(freeboard.getFrboTitle());
		findFreeBoard.setFrboContent(freeboard.getFrboContent());
		
		freeBoardService.insertFreeBoard(findFreeBoard);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "수정이 완료되었습니다.");
		
		
		
	}
	
	
	
	@DeleteMapping("/deleteFreeBoard/{frboSeq}")
	public @ResponseBody ResponseDTO<?> deleteFreeBoard(@PathVariable int frboSeq) {
		freeBoardService.deleteAllFreeComment(frboSeq);
		freeBoardService.deleteFreeBoard(frboSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "삭제가 완료되었습니다.");
	}
	

	
	
	
	
	
	//댓글
	
	
	@PostMapping("/insertFreeComent/{frboSeq}")
	public @ResponseBody ResponseDTO<?> insertFreeComment(HttpSession session, @RequestBody FreeComment freeComment, @PathVariable int frboSeq) {
		User user = (User) session.getAttribute("principal");
		FreeBoard findFreeBoard = freeBoardService.getFreeBoard(frboSeq);
		freeComment.setUser(user);
		freeComment.setFreeBoard(findFreeBoard);
		
		freeBoardService.insertFreeComment(freeComment);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 등록되었습니다.");
	}
	
	
	
	@DeleteMapping("/deleteFreeComment/{frboCoSeq}")
	public @ResponseBody ResponseDTO<?> deleteFreeComment(@PathVariable int frboCoSeq) {
		System.out.println("컨트롤라에서 딜리트가 되는"+frboCoSeq);
		freeBoardService.deleteFreeComment(frboCoSeq);
	
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 삭제되었습니다."); 
	}
	
	
}
