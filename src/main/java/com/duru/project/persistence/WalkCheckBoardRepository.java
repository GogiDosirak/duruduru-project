package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkCheckBoard;

public interface WalkCheckBoardRepository extends JpaRepository<WalkCheckBoard, Integer>{

}
