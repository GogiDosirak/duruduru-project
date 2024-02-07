package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.FreeBoard;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{
	

	Page<FreeBoard> findByFrboTitleContaining(String searchKeyword, Pageable pageable);
	
	Page<FreeBoard> findByFrboContentContaining(String searchKeyword, Pageable pageable);

}
