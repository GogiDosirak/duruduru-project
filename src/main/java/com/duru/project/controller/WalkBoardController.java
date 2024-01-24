
package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.duru.project.domain.User;
import com.duru.project.domain.WalkBoard;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.WalkBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WalkBoardController {
	
	@Autowired
	private WalkBoardService walkBoardService;
	
	
	
	@GetMapping("/walk")
	public String walkBoardList(Model model) {
		model.addAttribute("walkBoardList", walkBoardService.getWalkBoardList());
		return "board/walk/walkboardlist";
	}
	
	@GetMapping("/insertWalkBoard")
	public String insertWalkBoard() {
		return "board/walk/insertwalkboard";
	}
	
	@PostMapping("/insertWalkBoard")
	public @ResponseBody ResponseDTO<?> insertWalkBoard(@RequestBody WalkBoard walkboard, HttpSession session){
		User findUser = (User) session.getAttribute("principal");
		walkboard.setUser(findUser);
		walkBoardService.insertWalkBoard(walkboard);
		return new ResponseDTO<>(HttpStatus.OK.value(),"글 등록 완료되었습니다.");
	}
	
	@GetMapping("/getWalkBoard/{wabo_seq}")
	public String getWalkBoard(@PathVariable int wabo_seq, Model model) {
		WalkBoard findWalkBoard = walkBoardService.getWalkBoard(wabo_seq);
		model.addAttribute("walkBoard", findWalkBoard);
		return "board/walk/walkboard";
	}
	
	@GetMapping("/updateWalkBoard/{wabo_seq}")
	public String updatePost(@PathVariable int wabo_seq, Model model) {
		WalkBoard findWalkBoard =walkBoardService.getWalkBoard(wabo_seq);
		model.addAttribute("walkBoard", findWalkBoard);
		return "board/walk/updatewalkboard";
	}
	
	@PutMapping("/updateWalkBoard/{wabo_seq}")
	public @ResponseBody ResponseDTO<?> updateWalkBoard(@PathVariable int wabo_seq, @RequestBody WalkBoard walkboard){
		WalkBoard findWalkBoard =walkBoardService.getWalkBoard(wabo_seq);
		findWalkBoard.setWabo_title(walkboard.getWabo_title());
		findWalkBoard.setWabo_content(walkboard.getWabo_content());
		walkBoardService.insertWalkBoard(findWalkBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 수정 완료");
	}
	
	@DeleteMapping("/deleteWalkBoard/{wabo_seq}")
	public @ResponseBody ResponseDTO<?> deleteWalkBoard(@PathVariable int wabo_seq){
		walkBoardService.deleteWalkBoard(wabo_seq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제 완료");
	}

}
