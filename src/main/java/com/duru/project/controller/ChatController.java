package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.ChatUser;
import com.duru.project.domain.MessageType;
import com.duru.project.domain.User;
import com.duru.project.persistence.ChatRepository;
import com.duru.project.service.ChatDetailService;
import com.duru.project.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    // 아래에서 사용되는 convertAndSend 를 사용하기 위해서 서언
    // convertAndSend 는 객체를 인자로 넘겨주면 자동으로 Message 객체로 변환 후 도착지로 전송한다.
    private final SimpMessageSendingOperations template;

    @Autowired
    private ChatRepository repository;
    
    @Autowired
    private ChatDetailService chatDetailService;
   
    @Autowired
    private UserService userService;

    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload ChatUser chatUser, 
                        @Payload ChatRoom chatroom, 
                        ChatJoinId chatJoinId
                        ) {
    System.out.println("왔나?");
    //log.info("CHAT {}", chatUser);
    System.out.println(chatroom.getCrSeq());
    
    // 세션에서 현재 사용자 정보 가져오기
    User user = userService.getUser("yyjjmm2003"); // 적절한 방식으로 현재 사용자를 가져오는 메서드 호출
    
    // ChatJoinId 객체 설정
    chatJoinId.setCrSeq(chatroom.getCrSeq());
    chatJoinId.setUserSeq(user.getUserSeq());
    
    // ChatJoin 객체 가져오기
    ChatJoin chatjoin = chatDetailService.getChatJoin(chatJoinId);
    
    // ChatUser의 필드 설정
    chatUser.setCrSeq3(chatjoin);
    chatUser.setUserSeq3(chatjoin);
    chatUser.setCuMessage(chatUser.getCuMessage());
    chatUser.setCuCheck(false);
    chatUser.setCuRole(chatUser.getCuRole());
    chatUser.setCuSender(chatUser.getCuSender());
    
    repository.save(chatUser);
    // 채팅 메시지 전송
    template.convertAndSend("/sub/chat/room/" + chatroom.getCrSeq(), chatUser);
}

    
   

}