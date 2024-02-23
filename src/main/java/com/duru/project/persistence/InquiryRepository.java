package com.duru.project.persistence;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.FreeBoard;
import com.duru.project.domain.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
	Page<Inquiry> findByInquiryTitleContaining(String keyword, Pageable pageable);
	
	List<Inquiry> findByUser_UserSeq(int userSeq);
		
	
	

}
