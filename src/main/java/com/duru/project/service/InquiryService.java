package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.Inquiry;
import com.duru.project.persistence.InquiryCommentRepository;
import com.duru.project.persistence.InquiryRepository;

@Service
public class InquiryService {
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private InquiryCommentRepository inquiryCommentRepository;
	
	@Transactional(readOnly = true)
	public List<Inquiry> getInquiryList() {
		return inquiryRepository.findAll();
	}
	
	@Transactional
	public void insertInquiry(Inquiry inquiry) {
		inquiryRepository.save(inquiry);
	}
	
	@Transactional(readOnly = true)
	public Inquiry getInquiry(int inquirySeq) {
		Inquiry inquiry = inquiryRepository.findById(inquirySeq).get();
		return inquiry;
	}
	
	@Transactional
	public void updateInquiry(Inquiry inquiry) {
		inquiryRepository.save(inquiry);
	}
	
	@Transactional
	public void deleteInquiry(int inquirySeq) {
		inquiryCommentRepository.deleteByInquiry_InquirySeq(inquirySeq);
		inquiryRepository.deleteById(inquirySeq);
		
	}

}
