package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.FindPetBoardComment;
import com.duru.project.persistence.FindPetCommentRepository;

@Service
public class FindPetCommentService {
	
	@Autowired
	private FindPetCommentRepository findPetCommentRepository;
	
	@Transactional
	public void insertFindPetComment(FindPetBoardComment findPetComment) {
		findPetCommentRepository.save(findPetComment);
	}
	
	
	@Transactional(readOnly = true)
	public List<FindPetBoardComment> FindPetCommentList() {
		return findPetCommentRepository.findAll();
	}
	
	
	
	@Transactional
	public void deleteFindPetComment(int fpCoSeq) {
		findPetCommentRepository.deleteById(fpCoSeq);
	}
	
	
	@Transactional
	public void deleteAllFindPetComment(int fpSeq) {
		findPetCommentRepository.deleteByFindPetBoard_fpSeq(fpSeq);
	}

}
