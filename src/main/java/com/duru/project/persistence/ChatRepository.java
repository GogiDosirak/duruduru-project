package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.ChatUser;
import com.duru.project.domain.CheckType;
import com.duru.project.domain.MessageType;

public interface ChatRepository extends JpaRepository<ChatUser, Integer>{

	 List<ChatUser> findByChatJoin_Id_CrSeq(String crSeq, Sort sort);
	 List<ChatUser> findByChatJoin_Id_UserSeq(int userSeq); //사용 안함
	 List<ChatUser> findByChatJoin_Id_UserSeqAndCuCheckAndCuRole(int userSeq, CheckType cuCheck, MessageType cuRole);
	
}
