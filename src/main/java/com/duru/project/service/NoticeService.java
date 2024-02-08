package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Notice> getNoticeList(Pageable pageable){
		return noticeRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<Notice> searchNotice(String keyword, Pageable pageable) {
		return noticeRepository.findByNoticeTitleContaining(keyword, pageable);
	}
	
	@Transactional
	public void insertNotice(Notice notice) {
		noticeRepository.save(notice);
		
	}
	
	@Transactional
	public Notice getNotice(int noticeSeq) {
		Notice getNotice =  noticeRepository.findById(noticeSeq).get();
		getNotice.setNoticeCnt(getNotice.getNoticeCnt()+1);
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
