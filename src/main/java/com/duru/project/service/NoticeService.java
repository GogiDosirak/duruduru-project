package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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
	
	@Transactional(readOnly = true)
	public Notice getNotice(int noticeSeq) {
		Notice getNotice =  noticeRepository.findById(noticeSeq).get();
		return getNotice;
		
	}
	
	@Transactional
	public void deleteNotice(int noticeSeq) {
		noticeRepository.deleteById(noticeSeq);
	}
	
	@Transactional
	public void updateNotice(Notice notice) {
		noticeRepository.save(notice); //같은 게시글이라면 save하면 update됨
	}
	

}
