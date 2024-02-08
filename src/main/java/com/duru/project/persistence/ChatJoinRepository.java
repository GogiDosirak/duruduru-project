package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;

public interface ChatJoinRepository extends JpaRepository<ChatJoin, ChatJoinId>{
	

}
