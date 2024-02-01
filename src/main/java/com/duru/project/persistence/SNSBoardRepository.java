package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.SNSBoard;

@Repository
public interface SNSBoardRepository extends JpaRepository<SNSBoard, Integer>{
	
	 List<SNSBoard> findAllByOrderByLikeCntDesc();
}
