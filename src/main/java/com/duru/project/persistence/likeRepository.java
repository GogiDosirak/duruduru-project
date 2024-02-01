package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.LikeBoard;

@Repository
public interface likeRepository extends JpaRepository<LikeBoard, Integer>{
	
}
