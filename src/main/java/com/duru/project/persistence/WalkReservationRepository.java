package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.WalkReservation;

public interface WalkReservationRepository extends JpaRepository<WalkReservation, Integer>{
	
	List<WalkReservation> findBywareTitleContaining(String searchKeyword);

	List<WalkReservation> findBywareContentContaining(String searchKeyword);

}
