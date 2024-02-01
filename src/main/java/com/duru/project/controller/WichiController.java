package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duru.project.domain.SNSBoard;
import com.duru.project.domain.User;
import com.duru.project.service.WichiService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WichiController {
	@Autowired
	private WichiService wichiService;

	@GetMapping("/wichi")
	public String getwichi() {
		return "wichi";
	}

	@GetMapping("/wichi2")
	public String getBoardsOrderByDistanceAndRecent(Model model, HttpSession session) {
		// 세션에서 유저 정보 가져오기 (로그인한 사용자의 위치 정보를 사용)
		User user = (User) session.getAttribute("principal");
		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}

		// 서비스에서 거리와 최근성에 따라 정렬된 게시글 리스트 가져오기
		List<SNSBoard> sortedBoards = wichiService.getBoardsOrderByDistanceAndRecent(user.getLatitude(),
				user.getLongitude());

		// 모델에 정렬된 게시글 리스트 추가
		model.addAttribute("SNSList", sortedBoards);

		// 게시글 목록 페이지로 이동
		return "wichi2";
	}

}
