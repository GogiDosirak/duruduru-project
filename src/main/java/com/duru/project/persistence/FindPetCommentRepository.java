package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.FindPetBoardComment;

public interface FindPetCommentRepository extends JpaRepository<FindPetBoardComment, Integer>{
	
	void deleteByFindPetBoard_fpSeq(int fpSeq);

}
