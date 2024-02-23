package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.FindPetBoard;

public interface FindPetBoardRepository extends JpaRepository<FindPetBoard, Integer>{
	

}
