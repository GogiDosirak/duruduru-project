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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.FindPetBoard;
import com.duru.project.domain.FindPetBoardComment;
import com.duru.project.domain.RoleType;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.FindPetBoardService;
import com.duru.project.service.FindPetCommentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FindPetBoardController {

	
	@Autowired
	private FindPetBoardService findPetBoardService;
	
	@Autowired
	private FindPetCommentService findPetCommentService;
	

	@GetMapping("/insertFindPet")
	public String insertFindPet(HttpSession session) {
		User user = (User) session.getAttribute("principal");

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		return "board/findPet/insertFindPet";
	}
	

	
	
	@PostMapping("/insertFindPet")
	public String insertFindPet(FindPetBoard findPetBoard, MultipartFile file, HttpSession session) throws Exception {
		
		if(file.isEmpty()) {
			return "redirect:/findPetBoard";
			
		} else {
			User user = (User)session.getAttribute("principal");
			findPetBoard.setUser(user);
			findPetBoard.setFpCnt(0);
			findPetBoardService.insertFindPetBoard(findPetBoard, file);
			
		}
		
		return  "redirect:/findPetBoard";
		
	}

	
	@GetMapping("/getFindPetBoard/{fpSeq}")
	public String getFPetBoard(@PathVariable int fpSeq, Model model, HttpSession session) {
		User user = (User) session.getAttribute("principal");

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		
		FindPetBoard findFindPetBoard = findPetBoardService.getFindPetBoard(fpSeq);
		model.addAttribute("findFindPetBoard", findFindPetBoard);
		
		//조회수
		findPetBoardService.increacCnt(findFindPetBoard);
		
		//댓글
		List<FindPetBoardComment> findPetCommentList = findPetCommentService.FindPetCommentList();
		model.addAttribute("findPetCommentList", findPetCommentList);
		
		return "board/findPet/getFindPet";
	}
	
	
	@GetMapping("/updateFindPetBoard")
	public String updateFindPetBoard(@RequestParam("fpSeq") int fpSeq, Model model, HttpSession session) {
		User user = (User) session.getAttribute("principal");

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		FindPetBoard findFPetBoard = findPetBoardService.getFindPetBoard(fpSeq);
		model.addAttribute("findFPetBoard", findFPetBoard);
		return "board/findPet/updateFindPet";
	}
	
	
	
	@PostMapping("/updateFindPetBoard")
	public String updateFPetBoard(@RequestParam("fpSeq")int fpSeq, FindPetBoard findPetBoard, MultipartFile file) throws Exception { 
		findPetBoardService.updateFindPetBoard(fpSeq, findPetBoard, file);
		
		return "redirect:/getFindPetBoard/"+ fpSeq; 
		
	}
	
	
	//
	@DeleteMapping("/deleteFindPetBoard/{fpSeq}")
	public @ResponseBody ResponseDTO<?> deleteFPetBoard(@PathVariable int fpSeq) {
		
		findPetCommentService.deleteAllFindPetComment(fpSeq);
		
		findPetBoardService.deleteFindPetBoard(fpSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제가 완료되었습니다.");
	}
	
	
	
	
	
	
	
	
	//댓글
	
	
	@PostMapping("/insertFindPetComment/{fpSeq}")
	public @ResponseBody ResponseDTO<?> insertComment(@RequestBody FindPetBoardComment findPetComment, HttpSession session, @PathVariable int fpSeq) {
		User user = (User) session.getAttribute("principal");
		FindPetBoard findPetBoard = findPetBoardService.getFindPetBoard(fpSeq);
		findPetComment.setUser(user);
		findPetComment.setFindPetBoard(findPetBoard);
		
		findPetCommentService.insertFindPetComment(findPetComment);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글등록이 완료되었습니다.");
	}
	
	
	
	
	@DeleteMapping("/deleteFindPetComment/{fpCoSeq}")
	public @ResponseBody ResponseDTO<?> deleteComment(@PathVariable int fpCoSeq) {
		
		findPetCommentService.deleteFindPetComment(fpCoSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 삭제되었습니다.");
	}
	
	
	
	
	
		
	
	//위도, 경도 가까운순으로 검색, 페이징 되게(5km)이내
		@GetMapping("/findPetBoard")
		public String getFPetBoardList(Model model,
		                               @PageableDefault(size = 4, sort = "fpSeq", direction = Direction.DESC) Pageable pageable,
		                               String searchKeyword,
		                               HttpSession session) {
		    User user = (User) session.getAttribute("principal");

		    if (user == null) {
		        // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
		        return "redirect:/login";
		    }

		    Page<FindPetBoard> findPetBoardpage = null;

		    
		    //관리자는 모든 게시글을 볼 수 있게
		    if(user.getRole().equals(RoleType.ADMIN)) {
		    	findPetBoardpage = findPetBoardService.boardTitleSearchList(searchKeyword, pageable);
		    	if (searchKeyword != null && !searchKeyword.isEmpty()) {
			       System.out.println("이건 1번");
		    		// 검색어가 있는 경우
			        findPetBoardpage = findPetBoardService.boardTitleSearchList(
			            searchKeyword, pageable);
			    } else {
			        System.out.println("이건2번");
			    	// 검색어가 없는 경우
			        findPetBoardpage = findPetBoardService.usedAll(pageable);
			           
			    }
		    	//관리자가 아닌 로그인 유저인 경우
		    } else { 
		    
		    if (searchKeyword != null && !searchKeyword.isEmpty()) {
		       System.out.println("이건 3번");
		    	// 검색어가 있는 경우
		        findPetBoardpage = findPetBoardService.searchFPetBoardByKeywordAndOrderByDistance(
		            searchKeyword, user.getLongitude(), user.getLatitude(), pageable);
		    } else {
		    	System.out.println("이건 4번");
		        // 검색어가 없는 경우
		        findPetBoardpage = findPetBoardService.getFindPetBoardOrderByDistance(
		            user.getLongitude(), user.getLatitude(), pageable);
		    }
		    }
		    
		    //페이지 갯수 받아오기 위해 리스트로 받아온 것

		    model.addAttribute("searchKeyword", searchKeyword);
		    model.addAttribute("findPetBoardpage", findPetBoardpage);
		    return "board/findPet/findpetPage";
		
		}
}
