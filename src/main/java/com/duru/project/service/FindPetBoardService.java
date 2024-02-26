package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.FindPetBoard;
import com.duru.project.persistence.FindPetBoardRepository;

@Service
public class FindPetBoardService {
	
	@Autowired
	private FindPetBoardRepository findPetBoardRepository;
	
	@Transactional(readOnly = true)
	public List<FindPetBoard> findPetBoard(int userSeq) {
		return findPetBoardRepository.findByUser_UserSeq(userSeq);
		
	}

}
