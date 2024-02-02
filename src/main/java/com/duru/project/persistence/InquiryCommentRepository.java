package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.InquiryComment;

@Repository
public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Integer> {
	
	List<InquiryComment> findByInquiry_InquirySeq(int inquirySeq);
	void deleteByInquiry_InquirySeq(int inquirySeq);
}
