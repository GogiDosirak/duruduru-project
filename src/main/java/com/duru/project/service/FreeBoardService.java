package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.FreeBoard;
import com.duru.project.persistence.FreeBoardRepository;

@Service
public class FreeBoardService {

	
	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	
	@Transactional
	public void insertFreeBoard(FreeBoard freeboard) {
		freeBoardRepository.save(freeboard);
	}
	
	
	
	
	@Transactional(readOnly = true)
	public List<FreeBoard> freeBoardList() {
		List<FreeBoard> getFreeBoardList = freeBoardRepository.findAll();
		return getFreeBoardList;
	}
	
	
	
	@Transactional(readOnly = true)
	public FreeBoard getFreeBoard(int frboSeq) {
		FreeBoard findFreeBoard = freeBoardRepository.findById(frboSeq).orElseGet( () -> {
			return new FreeBoard();
		});
		return findFreeBoard;
	}
	
 }
