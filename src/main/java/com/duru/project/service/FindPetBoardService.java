package com.duru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duru.project.persistence.FindPetBoardRepository;

@Service
public class FindPetBoardService {
	
	@Autowired
	private FindPetBoardRepository findPetBoardRepository;

}
