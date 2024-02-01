package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.WalkBoard;

@Repository
public interface WalkBoardRepository extends JpaRepository<WalkBoard, Integer>{
	
	   Page<WalkBoard> findBywaboTitleContaining(String searchKeyword, Pageable pageable);
	   
	   Page<WalkBoard> findBywaboContentContaining(String searchKeyword, Pageable pageable);

}
