package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.WalkCheckBoard;

public interface WalkCheckBoardRepository extends JpaRepository<WalkCheckBoard, Integer>{
	
	List<WalkCheckBoard> findByUser_UserSeq(int userSeq);

}
