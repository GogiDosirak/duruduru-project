package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.InquiryComment;
import com.duru.project.persistence.InquiryCommentRepository;


@Service
public class InquiryCommentService {
	@Autowired
	private InquiryCommentRepository inquiryCommentRepository;
	
	@Transactional(readOnly = true)
	public List<InquiryComment> getInquiryCommentList(int inquirySeq) {
		List<InquiryComment> inquiryCommentList = inquiryCommentRepository.findByInquiry_InquirySeq(inquirySeq);
		return inquiryCommentList;
		
	}
	
	@Transactional
	public void insertInquiryComment(InquiryComment inquiryComment) {
		inquiryCommentRepository.save(inquiryComment);
	}
	

}
