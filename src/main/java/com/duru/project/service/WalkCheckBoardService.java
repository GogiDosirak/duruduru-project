package com.duru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.WalkCheckBoard;
import com.duru.project.persistence.WalkCheckBoardRepository;

@Service
public class WalkCheckBoardService {
	
	@Autowired
	private WalkCheckBoardRepository walkCheckBoardRepository;
	
	@Transactional
	public void insertUser(WalkCheckBoard walkCheckBoard) {
		walkCheckBoardRepository.save(walkCheckBoard);
	}
	
	@Transactional(readOnly = true)
	public Page<WalkCheckBoard> getwachList(Pageable pageable) {
		
		Page<WalkCheckBoard> getwachList = walkCheckBoardRepository.findAll(pageable);
		
		return getwachList;
	}
	
	

}
