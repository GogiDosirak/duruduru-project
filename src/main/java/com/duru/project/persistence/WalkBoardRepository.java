package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkBoard;

public interface WalkBoardRepository extends JpaRepository<WalkBoard, Integer>{

}
