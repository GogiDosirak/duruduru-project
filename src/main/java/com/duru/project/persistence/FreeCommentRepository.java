package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.FreeComment;

@Repository
public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer>{

	void deleteByFreeBoard_frboSeq(int frboSeq);
}
