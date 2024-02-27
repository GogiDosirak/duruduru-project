package com.duru.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.config.ChatJoinCustomRepository;
import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.ChatUser;
import com.duru.project.domain.CheckType;
import com.duru.project.domain.MessageType;
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
	
	
	//새로운 채팅방 생성
	@Transactional
	public void insertRoom(ChatRoom chatRoom) {
		chatRoomRepository.save(chatRoom);
	}
	
	//새로운 유저 채팅방 입장
	@Transactional
	public void insertJoin(ChatJoin chatjoin) {
		chatJoinRepository.save(chatjoin);
	}
	
	//채팅내역 저장
	@Transactional
	public void insertChat(ChatUser chatUser) {
		chatrepository.save(chatUser);
	}
	
	
	//유저가 참여한 채팅방목록
	@Transactional(readOnly = true)
	public List<ChatJoin> chatRoomList(int userSeq, String cjStatus) {
		List<ChatJoin> chatRoomList = chatJoinRepository.findByUser_userSeqAndCjStatus(userSeq, "ENTER");
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
	
	@Transactional(readOnly = true)
	public List<ChatUser> getChatUser(String crSeq) {
		List<ChatUser> chatUser = chatrepository.findByChatJoin_Id_CrSeq(crSeq, Sort.by(Sort.Direction.ASC, "cuSeq"));
		return chatUser;
	}
	
	//입장 할 때
	@Transactional(readOnly = true)
	public List<ChatUser> checkEnterUser(int userSeq, CheckType cuCheck, MessageType cuRole) {
		List<ChatUser> chatUser = chatrepository.findByChatJoin_Id_UserSeqAndCuCheckAndCuRole(userSeq, cuCheck, cuRole);
		return chatUser;
	}
	
	//채팅방 인원 제한
	@Transactional(readOnly = true)
	public List<ChatJoin> getJoinUserCount(String crSeq) {
		List<ChatJoin> chatJoin = chatJoinRepository.findByChatRoom_crSeq(crSeq);
		return chatJoin;
	}
	
	//채팅방 인원 조회
	@Transactional(readOnly = true)
	public List<ChatJoin> chatUserList(String crSeq, String cjStatus) {
		List<ChatJoin> chatJoin = chatJoinRepository.findByChatRoom_crSeqAndCjStatus(crSeq, cjStatus);
		return chatJoin;
	}
	
	//채팅방 재입장
	@Transactional(readOnly = true)
	public List<ChatJoin> reEnterChat(int userSeq, String cjStatus) {
		List<ChatJoin> chatJoin = chatJoinRepository.findByUser_userSeqAndCjStatus(userSeq, cjStatus);
		return chatJoin;
	}
	
	//1:1대화 중복 체크
	@Transactional
	public String checkOnebyone(int userSeq1, int userSeq2) {
		List<ChatJoin> chatjoin = chatJoinRepository.findByChatRoom_crSeqContaining("g");
		List<ChatJoin> checkUser = new ArrayList<ChatJoin>();
		for (ChatJoin firstUser : chatjoin) {
			if(firstUser.getUser().getUserSeq() == userSeq1) {
				checkUser.add(firstUser);
			}
		}
		for (ChatJoin secondUser : chatjoin) {
			if(secondUser.getUser().getUserSeq() == userSeq2) {
				for (ChatJoin finalCheck : checkUser) {
					if(secondUser.getChatRoom().getCrSeq().equals(finalCheck.getChatRoom().getCrSeq())) {
						return finalCheck.getChatRoom().getCrSeq();
					} 
				}
			}
		}
		return null;
		}
	}
	


