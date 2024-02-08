package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.WalkBoardComment;
import com.duru.project.persistence.WalkBoardCommentRepository;

@Service
public class WalkBoardCommentService {

	@Autowired
	WalkBoardCommentRepository walkBoardCommentRepository;
	
	@Transactional
	public void insertWalkComment(WalkBoardComment walkBoardComment) {
		walkBoardCommentRepository.save(walkBoardComment);
	}
	
	@Transactional
	public List<WalkBoardComment> getWalkCommentList(){
		return walkBoardCommentRepository.findAll();
	}
	
	@Transactional
	public void deleteWalkComment(int waboCoSeq) {
		walkBoardCommentRepository.deleteById(waboCoSeq);;
	}
	
	@Transactional
	public void deleteAllWalkComment(int waboSeq) {
		walkBoardCommentRepository.deleteByWalkBoard_waboSeq(waboSeq);
	}
}
