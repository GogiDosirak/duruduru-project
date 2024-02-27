package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkReservationComment;

public interface WalkReservationCommentRepository extends JpaRepository<WalkReservationComment, Integer>{
		
		void deleteByWalkReservation_wareSeq(int wareSeq);

}
