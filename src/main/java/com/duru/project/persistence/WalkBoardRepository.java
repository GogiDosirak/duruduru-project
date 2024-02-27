package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.WalkBoard;

@Repository
public interface WalkBoardRepository extends JpaRepository<WalkBoard, Integer> {

	List<WalkBoard> findBywaboTitleContaining(String searchKeyword);

	List<WalkBoard> findBywaboContentContaining(String searchKeyword);
	
	List<WalkBoard> findByUser_UserSeq(int userSeq);

}
