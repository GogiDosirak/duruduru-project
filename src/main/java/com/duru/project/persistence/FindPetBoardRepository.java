package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.FindPetBoard;

public interface FindPetBoardRepository extends JpaRepository<FindPetBoard, Integer>{
	
	List<FindPetBoard> findByUser_UserSeq(int userSeq);
	

}
