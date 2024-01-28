package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.FreeComment;
import com.duru.project.persistence.FreeBoardRepository;
import com.duru.project.persistence.FreeCommentRepository;


@Service
public class FreeBoardService {

	
	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	@Autowired
	FreeCommentRepository freeCommentRepository;
	
	
	@Transactional
	public void insertFreeBoard(FreeBoard freeboard) {
		freeBoardRepository.save(freeboard);
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<FreeBoard> freeBoardList(Pageable pageable) {
		return freeBoardRepository.findAll(pageable);
	}
	
	
	
//	@Transactional(readOnly = true)
//	public List<FreeBoard> freeBoardList() {
//		List<FreeBoard> getFreeBoardList = freeBoardRepository.findAll();
//		return getFreeBoardList;
//	}

	
	
	@Transactional(readOnly = true)
	public FreeBoard getFreeBoard(int frboSeq) {
		FreeBoard findFreeBoard = freeBoardRepository.findById(frboSeq).orElseGet( () -> {
			return new FreeBoard();
		});
		return findFreeBoard;
	}
	
	
	@Transactional
	public void deleteFreeBoard(int frboSeq) {
		freeBoardRepository.deleteById(frboSeq);
	}
	
	
	//조회수
	@Transactional
	public void increaceCnt(FreeBoard freeBoard) {
		freeBoard.setFrboCnt(freeBoard.getFrboCnt() + 1);
		freeBoardRepository.save(freeBoard);
	}
	
	
	@Transactional(readOnly = true)
	 public Page<FreeBoard> boardTitleSearchList(String searchKeyword, Pageable pageable){
	        return freeBoardRepository.findByFrboTitleContaining(searchKeyword, pageable);
	    }
	
	
	 @Transactional(readOnly = true)
	 public Page<FreeBoard> boardContentSearchList(String searchKeyword, Pageable pageable){
	        return freeBoardRepository.findByFrboContentContaining(searchKeyword, pageable);
	    }
	
	
	
	//댓글
	
	@Transactional
	public void insertFreeComment(FreeComment freeComment) {
		freeCommentRepository.save(freeComment);
	}
	
	
	@Transactional(readOnly = true)
	public List<FreeComment> FreeCommentList() {
		return freeCommentRepository.findAll();
	}
	
	
	@Transactional
	public void deleteFreeComment(int frboCoSeq) {
		freeCommentRepository.deleteById(frboCoSeq);
	}
	
	
	@Transactional
	public void deleteAllFreeComment(int frboSeq) {
		freeCommentRepository.deleteByFreeBoard_frboSeq(frboSeq);
	}
	
 }
