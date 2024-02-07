package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.SNSBoardComment;
@Repository
public interface SNSCommentRepository extends JpaRepository<SNSBoardComment, Integer> {

	void deleteBySnsBoard_snsboSeq(int snsboSeq);
}
