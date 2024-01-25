package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.UsedBoard;

@Repository
public interface UsedBoardRepository extends JpaRepository<UsedBoard, Integer>{

}
