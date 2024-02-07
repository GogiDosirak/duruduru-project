package com.duru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.EventBoard;
import com.duru.project.persistence.EventBoardRepository;

@Service
public class EventBoardService {

	@Autowired
	private EventBoardRepository eventBoardRepository;
	
	
	@Transactional
	public void insertEvent(EventBoard eventBoard) {
		eventBoardRepository.save(eventBoard);
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<EventBoard> eventBoardList(Pageable pageable) {
		return eventBoardRepository.findAll(pageable);
	}
	
	
	@Transactional(readOnly =  true)
	public EventBoard getEventBoard(int eventSeq) {
		EventBoard findEventBoard = eventBoardRepository.findById(eventSeq).orElseGet( () -> {
			return new EventBoard();
		});
		
		return findEventBoard;
	}
	
	
	
	@Transactional
	public void deleteEvent(int eventSeq) {
		eventBoardRepository.deleteById(eventSeq);
	}
	
	
	@Transactional(readOnly = true)
	public Page<EventBoard> eventTitleSearchList(String searchKeyword, Pageable pageable) {
		return eventBoardRepository.findByEventTitleContaining(searchKeyword, pageable);
	}
	
	
	@Transactional
	public void increaceEventCnt(EventBoard eventBoard) {
		eventBoard.setEventCnt(eventBoard.getEventCnt() + 1);
		eventBoardRepository.save(eventBoard);
	}
}
