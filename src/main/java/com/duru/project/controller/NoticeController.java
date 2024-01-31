package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Notice;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice(HttpSession session) {
		List<Notice> noticeList = noticeService.getNoticeList();
		session.setAttribute("noticeList", noticeList);
		return "cs/notice/notice";
	}
	
	@GetMapping("/insertNotice")
	public String insertNotice() {
		return "cs/notice/insertNotice";
	}
	
	@PostMapping("/insertNotice")
	public @ResponseBody ResponseDTO<?> insertNotice(@RequestBody Notice notice) {
		noticeService.insertNotice(notice);
		return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항 등록이 완료되었습니다.");
		
	}
	

}
