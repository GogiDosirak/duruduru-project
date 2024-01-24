package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkBoard;

public interface WalkBoardRepository extends JpaRepository<WalkBoard, Integer>{
	
	   Page<WalkBoard> findBywaboTitleContaining(String searchTitle, Pageable pageable);
	   
	   Page<WalkBoard> findBywaboContentContaining(String searchContent, Pageable pageable);

}
