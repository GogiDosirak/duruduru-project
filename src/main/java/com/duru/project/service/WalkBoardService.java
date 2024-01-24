package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.WalkBoard;
import com.duru.project.persistence.WalkBoardRepository;

@Service
public class WalkBoardService {
	
	@Autowired
	WalkBoardRepository walkBoardRepository;
	
	@Transactional
	public void insertWalkBoard(WalkBoard walkboard) {
		walkBoardRepository.save(walkboard);
	}
	
	@Transactional(readOnly = true)
	public List<WalkBoard> getWalkBoardList(){
		return walkBoardRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<WalkBoard> getWalkBoardListPage(Pageable pageable){
		return walkBoardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public WalkBoard getWalkBoard(int waboSeq) {
		WalkBoard findWalkBoard = walkBoardRepository.findById(waboSeq).orElseGet(()->{
			return new WalkBoard();
		});
		return findWalkBoard;
	}
	
	@Transactional
	public void deleteWalkBoard(int waboSeq) {
		walkBoardRepository.deleteById(waboSeq);
	}
	
	@Transactional
	public void increaseCnt(WalkBoard walkBoard) {
		walkBoard.setWaboCnt(walkBoard.getWaboCnt()+1);
		walkBoardRepository.save(walkBoard);
	}
	
	

}
