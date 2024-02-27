package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.WalkReservationComment;
import com.duru.project.persistence.WalkReservationCommentRepository;

@Service
public class WalkReservationCommentService {
	
	@Autowired
	private WalkReservationCommentRepository walkReservationCommentRepository;
	
	@Transactional
	public void insertWalkComment(WalkReservationComment walkReservationComment) {
		walkReservationCommentRepository.save(walkReservationComment);
	}
	
	@Transactional
	public List<WalkReservationComment> getWalkCommentList(){
		return walkReservationCommentRepository.findAll();
	}
	
	@Transactional
	public void deleteWalkComment(int wareCoSeq) {
		walkReservationCommentRepository.deleteById(wareCoSeq);;
	}
	
	@Transactional
	public void deleteAllWalkComment(int wareSeq) {
		walkReservationCommentRepository.deleteByWalkReservation_wareSeq(wareSeq);
	}

}
