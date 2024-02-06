package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.duru.project.domain.UsedBoard;
import com.duru.project.domain.UsedComment;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.UsedBoardService;

import jakarta.servlet.http.HttpSession;




@Controller
public class UsedBoardController {
	
	@Autowired
	UsedBoardService usedBoardService;
	
	//검색 기능 없이 페이징 
//	@GetMapping("/usedboard")
//	public String getProdctList(HttpSession session, @PageableDefault(size=8, sort="usboSeq", direction = Direction.DESC) Pageable pageable) {
//		Page<UsedBoard> usedBoardList = usedBoardService.usedBoardList(pageable);
//		session.setAttribute("usedBoardList", usedBoardList);
//		return "board/used/usedBoardList";
//	}

	
	
	@GetMapping("/wichi")
	   public String getwichi() {
	      return "wichi";
	   }
	
	
	//검색 + 페이징
//	@GetMapping("/usedboard")
//	public String usedBoardList(Model model, @PageableDefault(size =4, sort = "usboSeq", direction = Direction.DESC) Pageable pageable, String searchKeyword) {
//		Page<UsedBoard> list = null;
//
//		if (searchKeyword == null) {
//			list = usedBoardService.usedBoardList(pageable); // 기존의 리스트보여줌
//		} else {
//			list = usedBoardService.boardTitleSearchList(searchKeyword, pageable);  // 검색리스트반환
//		}
//
//	    model.addAttribute("searchKeyword", searchKeyword);
//		model.addAttribute("usedBoardList", list);
//		return "board/used/usedBoardList";
//
//	}
	
	
	

	@GetMapping("/insertUsedBoard")
	public String insertUsedBoard() {
		return "board/used/insertUsedBoard";
	}
	

	
	
	@PostMapping("/insertUsedBoard")
	public String insertUsedBoard(UsedBoard usedBoard, MultipartFile file, HttpSession session) throws Exception {
		
		if(file.isEmpty()) {
			return "redirect:/usedboard";
			
		} else {
			User user = (User)session.getAttribute("principal");
			usedBoard.setUser(user);
			usedBoardService.insertUsedBoard(usedBoard, file);
			
		}
		
		return  "redirect:/usedboard";
		
	}

	
	@GetMapping("/getUsedBoard/{usboSeq}")
	public String getUsedBoard(@PathVariable int usboSeq, Model model) {
		UsedBoard findUsedBoard = usedBoardService.getUsedBoard(usboSeq);
		model.addAttribute("findUsedBoard", findUsedBoard);
		
		//조회수
		usedBoardService.increacCnt(findUsedBoard);
		
		//댓글
		List<UsedComment> usedCommentList = usedBoardService.usedCommentList();
		model.addAttribute("usedCommentList", usedCommentList);
		
		return "board/used/getUsedBoard";
	}
	
	
	@GetMapping("/updateUsedBoard")
	public String updateUsedBoard(@RequestParam("usboSeq") int usboSeq, Model model) {
		UsedBoard findUsedBoard = usedBoardService.getUsedBoard(usboSeq);
		model.addAttribute("findUsedBoard", findUsedBoard);
		return "board/used/updateUsedBoard";
	}
	
	
	
	@PostMapping("/updateUsedBoard") //file 업로드를 하는 방식으론 form밖에 사용할 줄 몰랐다... js로 하고싶었는데 한계를 느끼고 form 사용
	public String updateUsedBoard(@RequestParam("usboSeq")int usboSeq, UsedBoard usedBoard, MultipartFile file) throws Exception { //form엔 Post,Get 방식밖에 없으므로 PutMapping을 사용 못하고 Postmapping을 사용하였다.
		usedBoardService.updateUsedBoard(usboSeq, usedBoard, file);//매개변수로 셋을 받아서 그대로 update
		
		return "redirect:/getUsedBoard/"+usboSeq; //그 후, mall로 바로 보내줘야 GetMapping 돼서 Session에 수정된 정보가 set됨. 그러므로 redirect 사용 
		
	}
	
	
	
	@DeleteMapping("/deleteUsedBoard/{usboSeq}")
	public @ResponseBody ResponseDTO<?> deleteUsedBoard(@PathVariable int usboSeq) {
		
		usedBoardService.deleteAllUsedComment(usboSeq);
		
		usedBoardService.deleteUsedBoard(usboSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제가 완료되었습니다.");
	}
	
	
	
	
	
	
	
	
	//댓글
	
	
	@PostMapping("/insertUsedComment/{usboSeq}")
	public @ResponseBody ResponseDTO<?> insertComment(@RequestBody UsedComment usedComment, HttpSession session, @PathVariable int usboSeq) {
		User user = (User) session.getAttribute("principal");
		UsedBoard usedBoard = usedBoardService.getUsedBoard(usboSeq);
		usedComment.setUser(user);
		usedComment.setUsedBoard(usedBoard);
		
		usedBoardService.insertUsedComment(usedComment);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글등록이 완료되었습니다.");
	}
	
	
	
	
	@DeleteMapping("/deleteUsedComment/{usboCoSeq}")
	public @ResponseBody ResponseDTO<?> deleteComment(@PathVariable int usboCoSeq) {
		
		System.out.println("삭제되냐?" + usboCoSeq);
		
		usedBoardService.deleteUsedComment(usboCoSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글이 삭제되었습니다.");
	}
	
	
	
	
	
		
	
	//위도, 경도 가까운순으로 검색, 페이징 되게(5km)이내
	@GetMapping("/usedboard")
	public String getUsedBoardList(Model model,
	                               @PageableDefault(size = 4) Pageable pageable,
	                               String searchKeyword,
	                               HttpSession session) {
	    User user = (User) session.getAttribute("principal");

	    if (user == null) {
	        // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
	        return "redirect:/login";
	    }

	    Page<UsedBoard> usedboardpage = null;

	    if (searchKeyword != null && !searchKeyword.isEmpty()) {
	        // 검색어가 있는 경우
	        usedboardpage = usedBoardService.searchUsedBoardByKeywordAndOrderByDistance(
	            searchKeyword, user.getLatitude(), user.getLongitude(), pageable);
	    } else {
	        // 검색어가 없는 경우
	        usedboardpage = usedBoardService.getUsedBoardOrderByDistance(
	            user.getLatitude(), user.getLongitude(), pageable);
	    }

	    model.addAttribute("searchKeyword", searchKeyword);
	    model.addAttribute("usedboardpage", usedboardpage);

	    return "board/used/usedBoardList";
	}

	
}
