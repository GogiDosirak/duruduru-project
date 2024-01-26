package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.FreeBoard;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{

}