package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkBoardComment;

public interface WalkBoardCommentRepository extends JpaRepository<WalkBoardComment, Integer>{

	void deleteByWalkBoard_waboSeq(int waboSeq);
}
