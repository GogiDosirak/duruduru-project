package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.Inquiry;
import com.duru.project.domain.Product;
import com.duru.project.persistence.InquiryCommentRepository;
import com.duru.project.persistence.InquiryRepository;

@Service
public class InquiryService {
	@Autowired
	private InquiryRepository inquiryRepository;
	@Autowired
	private InquiryCommentRepository inquiryCommentRepository;
	
	@Transactional(readOnly = true)
	public Page<Inquiry> searchInquiry(String keyword, Pageable pageable) {
		Page<Inquiry> inquirySearchList = inquiryRepository.findByInquiryTitleContaining(keyword, pageable);
		return inquirySearchList;
	}
	@Transactional(readOnly = true)
	public Page<Inquiry> getInquiryList(Pageable pageable) {
		return inquiryRepository.findAll(pageable);
	}
	
	@Transactional
	public void insertInquiry(Inquiry inquiry) {
		inquiryRepository.save(inquiry);
	}
	
	@Transactional
	public Inquiry getInquiry(int inquirySeq) {
		Inquiry inquiry = inquiryRepository.findById(inquirySeq).get();
		inquiry.setInquiryCnt(inquiry.getInquiryCnt()+1);
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
