package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class InquiryCommentController {
	@Autowired
	private InquiryCommentService inquiryCommentService;
	@Autowired
	private InquiryService inquiryService;
	
	@PostMapping("/insertInquiryComment/{inquirySeq}")
	public @ResponseBody ResponseDTO<?> insertInquiryComment(@PathVariable int inquirySeq, @RequestBody InquiryComment inquiryComment, HttpSession session) {
		User user = (User) session.getAttribute("principal");
		Inquiry inquiry = inquiryService.getInquiry(inquirySeq);
		inquiryComment.setInquiry(inquiry);
		inquiryComment.setUser(user);
		inquiryCommentService.insertInquiryComment(inquiryComment);
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글 등록 완료");
	}
	
	
	

}
