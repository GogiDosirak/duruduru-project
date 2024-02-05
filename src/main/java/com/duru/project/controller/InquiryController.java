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

import com.duru.project.domain.Inquiry;
import com.duru.project.domain.InquiryComment;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.InquiryCommentService;
import com.duru.project.service.InquiryService;


import jakarta.servlet.http.HttpSession;

@Controller
public class InquiryController {
	@Autowired
	private InquiryService inquiryService;
	@Autowired
	private InquiryCommentService inquiryCommentService;
	
	@GetMapping("/inquiry")
	public String inquiry(HttpSession session, @PageableDefault(size=5, sort="inquirySeq", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Inquiry> inquiryList = inquiryService.getInquiryList(pageable);
		session.setAttribute("inquiryList", inquiryList);
		return "cs/inquiry/inquiry";
	}
	
	@GetMapping("/inquiry/searchInquiry")
	public String searchInquiry(String keyword, Model model, @PageableDefault(size=5, sort="inquirySeq", direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Inquiry> inquirySearchList = inquiryService.searchInquiry(keyword, pageable);
		model.addAttribute("keyword", keyword);
		model.addAttribute("inquirySearchList", inquirySearchList);
		return "cs/inquiry/searchInquiry";
	}
	
	@GetMapping("/insertInquiry")
	public String insertInquiry() {
		return "cs/inquiry/insertInquiry";
	}
	
	@PostMapping("/insertInquiry")
	public @ResponseBody ResponseDTO<?> insertInquiry(@RequestBody Inquiry inquiry, HttpSession session) {
		User user = (User) session.getAttribute("principal");
		inquiry.setUser(user);
		inquiryService.insertInquiry(inquiry);
		return new ResponseDTO<>(HttpStatus.OK.value(), "1:1문의 등록 완료");
	}
	
	@GetMapping("/getInquiry/{inquirySeq}")
	public String getInquiry(@PathVariable int inquirySeq, Model model) {
		Inquiry getInquiry = inquiryService.getInquiry(inquirySeq);
		List<InquiryComment> inquiryCommentList = inquiryCommentService.getInquiryCommentList(inquirySeq);
		model.addAttribute("getInquiry", getInquiry);
		model.addAttribute("inquiryCommentList", inquiryCommentList);
		return "cs/inquiry/getInquiry";
		
	}
	
	@DeleteMapping("/deleteInquiry/{inquirySeq}")
	public @ResponseBody ResponseDTO<?> deleteInquiry(@PathVariable int inquirySeq) {
		inquiryService.deleteInquiry(inquirySeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "1:1문의 삭제 완료");
	}
	
	@GetMapping("/updateInquiry/{inquirySeq}")
	public String updateInquiry(@PathVariable int inquirySeq, Model model) {
		Inquiry getInquiry = inquiryService.getInquiry(inquirySeq);
		model.addAttribute("getInquiry",getInquiry);
		return "cs/inquiry/updateInquiry";
	}
	
	@PutMapping("/updateInquiry/{inquirySeq}")
	public @ResponseBody ResponseDTO<?> updateInquiry(@PathVariable int inquirySeq, @RequestBody Inquiry inquiry) {
		Inquiry updateInquiry = inquiryService.getInquiry(inquirySeq);
		updateInquiry.setInquiryContent(inquiry.getInquiryContent());
		updateInquiry.setInquiryTitle(inquiry.getInquiryTitle());
		inquiryService.updateInquiry(updateInquiry);
		return new ResponseDTO<>(HttpStatus.OK.value(), "1:1문의 수정 완료");
		
	}
	
	


}
