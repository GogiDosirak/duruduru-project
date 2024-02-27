package com.duru.project.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;

public interface ChatJoinRepository extends JpaRepository<ChatJoin, ChatJoinId>{
	
	List<ChatJoin> findByUser_userSeq(int userSeq);
	List<ChatJoin> findByChatRoom_crSeq(String crSeq);
	List<ChatJoin> findByChatRoom_crSeqContaining(String searchString);
	List<ChatJoin> findByChatRoom_crSeqAndCjStatus(String crSeq, String cjStatus);
	List<ChatJoin> findByUser_userSeqAndCjStatus(int userSeq, String cjStatus);
	
}
