package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.EventBoard;

@Repository
public interface EventBoardRepository extends JpaRepository<EventBoard, Integer>{

	Page<EventBoard> findByEventTitleContaining(String searchKeyword, Pageable pageable);
}
