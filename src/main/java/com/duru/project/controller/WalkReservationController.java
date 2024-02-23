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
import com.duru.project.domain.WalkReservation;
import com.duru.project.domain.WalkReservationComment;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.WalkReservationCommentService;
import com.duru.project.service.WalkReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WalkReservationController {

	@Autowired
	private WalkReservationService walkReservationService;
	
	@Autowired
	private WalkReservationCommentService walkReservationCommentService;
	
	// walkReservationPage // walkReservationSearchPage
		@GetMapping("/walkReservation")
		public String getWalkReservationListPage(Model model, @PageableDefault(size = 4) Pageable pageable,
				@RequestParam(required = false) String searchKeyword, HttpSession session) {

			User findUser = (User) session.getAttribute("principal");
			double userLatitude = findUser.getLatitude();
			double userLongitude = findUser.getLongitude();

			Page<WalkReservation> walkReservationpage = null;
			
			   // 관리자 여부에 따라 페이지 조회를 다르게 처리
		    if (findUser.getRole().equals(RoleType.ADMIN)) {
		        walkReservationpage = walkReservationService.getWalkReservationListPage(pageable);
		    } else {
		        // 유저일 때 검색어 존재 확인
		        if (searchKeyword == null) {
		            walkReservationpage = walkReservationService.getWalkReservationsOrderByDistanceAndRecentWithPaging(userLatitude,
		                    userLongitude, searchKeyword, pageable);
		        } else {
		            walkReservationpage = walkReservationService.getWalkReservationsOrderByDistanceAndRecentWithPaging(userLatitude,
		                    userLongitude, searchKeyword, pageable);
		        }
		    }

			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("walkReservationpage", walkReservationpage);

			return "board/walkReservation/walkReservationPage";
		}

		// insert11
		@GetMapping("/insertWalkReservation")
		public String insertWalkReservation() {
			return "board/walkReservation/insertWalkReservation";
		}

		// insert22
		@PostMapping("/insertWalkReservation")
		public @ResponseBody ResponseDTO<?> insertWalkReservation(@RequestBody WalkReservation walkReservation, HttpSession session) {
			User findUser = (User) session.getAttribute("principal");
			walkReservation.setUser(findUser);
			walkReservationService.insertWalkReservation(walkReservation);
			return new ResponseDTO<>(HttpStatus.OK.value(), "글 등록 완료되었습니다.");
		}

		// getReservation
		@GetMapping("/getWalkReservation/{wareSeq}")
		public String getWalkReservation(@PathVariable int wareSeq, Model model) {
			WalkReservation findWalkReservation = walkReservationService.getWalkReservation(wareSeq);
			walkReservationService.increaseCnt(findWalkReservation);
			model.addAttribute("walkReservation", findWalkReservation);
			model.addAttribute("walkReservationCommentList", walkReservationCommentService.getWalkCommentList());
			return "board/walkReservation/getWalkReservation";
		}

		// updateReservation11
		@GetMapping("/updateWalkReservation/{wareSeq}")
		public String updatePost(@PathVariable int wareSeq, Model model) {
			WalkReservation findWalkReservation = walkReservationService.getWalkReservation(wareSeq);
			model.addAttribute("walkReservation", findWalkReservation);
			return "board/walkReservation/updateWalkReservation";
		}

		// updateReservation22
		@PutMapping("/updateWalkReservation/{wareSeq}")
		public @ResponseBody ResponseDTO<?> updateWalkReservation(@PathVariable int wareSeq, @RequestBody WalkReservation walkReservation) {
			WalkReservation findWalkReservation = walkReservationService.getWalkReservation(wareSeq);
			findWalkReservation.setWareTitle(walkReservation.getWareTitle());
			findWalkReservation.setWareContent(walkReservation.getWareContent());
			walkReservationService.insertWalkReservation(findWalkReservation);
			return new ResponseDTO<>(HttpStatus.OK.value(), "글 수정 완료");
		}

		// deleteReservation
		@DeleteMapping("/deleteWalkReservation/{wareSeq}")
		public @ResponseBody ResponseDTO<?> deleteWalkReservation(@PathVariable int wareSeq) {
			walkReservationCommentService.deleteAllWalkComment(wareSeq);
			walkReservationService.deleteWalkReservation(wareSeq);
			return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제 완료");
		}

		// 댓글

		@PostMapping("/insertWalkReservationComment/{wareSeq}")
		public @ResponseBody ResponseDTO<?> insertWalkReservationComment(@PathVariable int wareSeq,
				@RequestBody WalkReservationComment walkReservationComment, HttpSession session) {
			User findUser = (User) session.getAttribute("principal");
			walkReservationComment.setUser(findUser);
			WalkReservation findWalkReservation = walkReservationService.getWalkReservation(wareSeq);
			walkReservationComment.setWalkReservation(findWalkReservation);
			walkReservationCommentService.insertWalkComment(walkReservationComment);
			return new ResponseDTO<>(HttpStatus.OK.value(), "댓글등록이 완료 되었습니다.");
		}

		@DeleteMapping("/deleteWalkReservationComment/{wareCoSeq}")
		public @ResponseBody ResponseDTO<?> deleteComment(@PathVariable int wareCoSeq) {
			walkReservationCommentService.deleteWalkComment(wareCoSeq);
			return new ResponseDTO<>(HttpStatus.OK.value(), "댓글삭제가 완료 되었습니다.");
		}
}
