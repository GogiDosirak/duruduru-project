package com.duru.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.ChatUser;
import com.duru.project.persistence.ChatJoinRepository;
import com.duru.project.persistence.ChatRepository;
import com.duru.project.persistence.ChatRoomRepository;

@Service
public class ChatDetailService {
	
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private ChatJoinRepository chatJoinRepository;
	
	@Autowired
	private ChatRepository chatrepository;
	
	@Transactional
	public void insertRoom(ChatRoom chatRoom) {
		chatRoomRepository.save(chatRoom);
		
	}

	@Transactional
	public void insertJoin(ChatJoin chatjoin) {
		
		chatJoinRepository.save(chatjoin);
	}
	
	@Transactional
	public void insertChat(ChatUser chatUser) {
		chatrepository.save(chatUser);
	}
	
	
	@Transactional(readOnly = true)
	public List<ChatRoom> chatRoomList() {
		List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
		return chatRoomList;
	}

	
	@Transactional(readOnly = true)
	public ChatRoom getChatroom(String crSeq) {
		ChatRoom getChatroom = chatRoomRepository.findById(crSeq).get();
		return getChatroom;
	}
	
	@Transactional(readOnly = true)
	public ChatJoin getChatJoin(ChatJoinId chatJoinId) {
		ChatJoin chatJoin = chatJoinRepository.findById(chatJoinId).get();
		return chatJoin;
	}

}
