package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.Notice;
import com.duru.project.persistence.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Transactional(readOnly = true)
	public List<Notice> getNoticeList(){
		return noticeRepository.findAll();
	}
	
	@Transactional
	public void insertNotice(Notice notice) {
		noticeRepository.save(notice);
		
	}
	

}
