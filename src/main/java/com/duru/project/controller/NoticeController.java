package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.duru.project.domain.Notice;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice(HttpSession session, @PageableDefault(size = 5, sort = "noticeSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Notice> noticeList = noticeService.getNoticeList(pageable);
		session.setAttribute("noticeList", noticeList);
		return "cs/notice/notice";
	}
	
	@GetMapping("/notice/searchNotice")
	public String searchNotice(String keyword, Model model, @PageableDefault(size=5, sort="noticeSeq",direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Notice> noticeSearchList = noticeService.searchNotice(keyword, pageable);
		model.addAttribute("keyword", keyword);
		model.addAttribute("noticeSearchList", noticeSearchList);
		return "cs/notice/searchNotice";
	}
	
	@GetMapping("/insertNotice")
	public String insertNotice() {
		return "cs/notice/insertNotice";
	}
	
	@PostMapping("/insertNotice")
	public @ResponseBody ResponseDTO<?> insertNotice(@RequestBody Notice notice, HttpSession session) {
		User user = (User) session.getAttribute("principal");
		notice.setUser(user);
		noticeService.insertNotice(notice);
		return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항 등록이 완료되었습니다.");
		
	}
	
	@GetMapping("/getNotice/{noticeSeq}")
	public String getNotice(@PathVariable int noticeSeq, Model model) {
		Notice getNotice = noticeService.getNotice(noticeSeq);
		model.addAttribute("getNotice", getNotice);
		return "cs/notice/getNotice";
	}
	
	@DeleteMapping("/deleteNotice/{noticeSeq}")
	public @ResponseBody ResponseDTO<?> deleteNotice(@PathVariable int noticeSeq) {
		noticeService.deleteNotice(noticeSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(),"공지사항 삭제 완료");
	}
	
	@GetMapping("/updateNotice/{noticeSeq}")
	public String updateNotice(@PathVariable int noticeSeq, Model model) {
		Notice getNotice = noticeService.getNotice(noticeSeq);
		model.addAttribute("getNotice", getNotice);
		return "cs/notice/updateNotice";
	}
	
	@PutMapping("/updateNotice/{noticeSeq}")
	public @ResponseBody ResponseDTO<?> updateNotice(@PathVariable int noticeSeq, @RequestBody Notice notice) {
		Notice updateNotice = noticeService.getNotice(noticeSeq); //noticeSeq 받아서 해당 공지사항 게시글 찾고
		updateNotice.setNoticeContent(notice.getNoticeContent());
		updateNotice.setNoticeTitle(notice.getNoticeTitle()); // 매개변수로 받은 내용들 set해주기
		noticeService.updateNotice(updateNotice); // 그 후 수정내용 저장
		return new ResponseDTO<>(HttpStatus.OK.value(),"공지사항 수정 완료");
	}
	

}
