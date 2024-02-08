
package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.RoleType;
import com.duru.project.domain.User;
import com.duru.project.domain.WalkBoard;
import com.duru.project.domain.WalkBoardComment;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.WalkBoardCommentService;
import com.duru.project.service.WalkBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WalkBoardController {

	@Autowired
	private WalkBoardService walkBoardService;

	@Autowired
	private WalkBoardCommentService walkBoardCommentService;

	// walkBoardPage // walkBoardSearchPage
	@GetMapping("/walk")
	public String getWalkBoardListPage(Model model, @PageableDefault(size = 4) Pageable pageable,
			@RequestParam(required = false) String searchKeyword, HttpSession session) {

		User findUser = (User) session.getAttribute("principal");
		double userLatitude = findUser.getLatitude();
		double userLongitude = findUser.getLongitude();

		Page<WalkBoard> walkboardpage = null;
		
		   // 관리자 여부에 따라 페이지 조회를 다르게 처리
	    if (findUser.getRole().equals(RoleType.ADMIN)) {
	        walkboardpage = walkBoardService.getWalkBoardListPage(pageable);
	    } else {
	        // 유저일 때 검색어 존재 확인
	        if (searchKeyword == null) {
	            walkboardpage = walkBoardService.getWalkBoardsOrderByDistanceAndRecentWithPaging(userLatitude,
	                    userLongitude, searchKeyword, pageable);
	        } else {
	            walkboardpage = walkBoardService.getWalkBoardsOrderByDistanceAndRecentWithPaging(userLatitude,
	                    userLongitude, searchKeyword, pageable);
	        }
	    }

		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("walkboardpage", walkboardpage);

		return "board/walk/walkboardpage";
	}

	// insert11
	@GetMapping("/insertWalkBoard")
	public String insertWalkBoard() {
		return "board/walk/insertwalkboard";
	}

	// insert22
	@PostMapping("/insertWalkBoard")
	public @ResponseBody ResponseDTO<?> insertWalkBoard(@RequestBody WalkBoard walkboard, HttpSession session) {
		User findUser = (User) session.getAttribute("principal");
		walkboard.setUser(findUser);
		walkBoardService.insertWalkBoard(walkboard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 등록 완료되었습니다.");
	}

	// getBoard
	@GetMapping("/getWalkBoard/{waboSeq}")
	public String getWalkBoard(@PathVariable int waboSeq, Model model) {
		WalkBoard findWalkBoard = walkBoardService.getWalkBoard(waboSeq);
		walkBoardService.increaseCnt(findWalkBoard);
		model.addAttribute("walkBoard", findWalkBoard);
		model.addAttribute("walkBoardCommentList", walkBoardCommentService.getWalkCommentList());
		return "board/walk/walkboard";
	}

	// updateBoard11
	@GetMapping("/updateWalkBoard/{waboSeq}")
	public String updatePost(@PathVariable int waboSeq, Model model) {
		WalkBoard findWalkBoard = walkBoardService.getWalkBoard(waboSeq);
		model.addAttribute("walkBoard", findWalkBoard);
		return "board/walk/updatewalkboard";
	}

	// updateBoard22
	@PutMapping("/updateWalkBoard/{waboSeq}")
	public @ResponseBody ResponseDTO<?> updateWalkBoard(@PathVariable int waboSeq, @RequestBody WalkBoard walkboard) {
		WalkBoard findWalkBoard = walkBoardService.getWalkBoard(waboSeq);
		findWalkBoard.setWaboTitle(walkboard.getWaboTitle());
		findWalkBoard.setWaboContent(walkboard.getWaboContent());
		walkBoardService.insertWalkBoard(findWalkBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 수정 완료");
	}

	// deleteBoard
	@DeleteMapping("/deleteWalkBoard/{waboSeq}")
	public @ResponseBody ResponseDTO<?> deleteWalkBoard(@PathVariable int waboSeq) {
		walkBoardCommentService.deleteAllWalkComment(waboSeq);
		walkBoardService.deleteWalkBoard(waboSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제 완료");
	}

	// 댓글

	@PostMapping("/insertWalkBoardComment/{waboSeq}")
	public @ResponseBody ResponseDTO<?> insertWalkBoardComment(@PathVariable int waboSeq,
			@RequestBody WalkBoardComment walkBoardComment, HttpSession session) {
		User findUser = (User) session.getAttribute("principal");
		walkBoardComment.setUser(findUser);
		WalkBoard findWalkBoard = walkBoardService.getWalkBoard(waboSeq);
		walkBoardComment.setWalkBoard(findWalkBoard);
		walkBoardCommentService.insertWalkComment(walkBoardComment);
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글등록이 완료 되었습니다.");
	}

	@DeleteMapping("/deleteComment/{waboCoSeq}")
	public @ResponseBody ResponseDTO<?> deleteComment(@PathVariable int waboCoSeq) {
		walkBoardCommentService.deleteWalkComment(waboCoSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글삭제가 완료 되었습니다.");
	}

}
