package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.ChatUser;
import com.duru.project.domain.CheckType;
import com.duru.project.domain.MessageType;
import com.duru.project.domain.User;
import com.duru.project.persistence.ChatRepository;
import com.duru.project.service.ChatDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {

    // 아래에서 사용되는 convertAndSend 를 사용하기 위해서 서언
    // convertAndSend 는 객체를 인자로 넘겨주면 자동으로 Message 객체로 변환 후 도착지로 전송한다.
    private final SimpMessageSendingOperations template;
    
    @Autowired
    private ChatDetailService chatDetailService;
    
    
    //입장 알림
    @MessageMapping("/chat/enterUser")
    public void enterUser(@Payload ChatUser chatUser,
    		@Payload ChatRoom chatroom, 
            @Payload User user,
            ChatJoinId chatJoinId) {
    	  // ChatJoinId 객체 설정
    	if(chatDetailService.checkEnterUser(user.getUserSeq(), CheckType.ENTER, MessageType.ENTER).size() == 0) {
    		
    	if(chatDetailService.reEnterChat(user.getUserSeq(), "LEAVE").size() != 0) {
    		ChatJoin chatJoin = chatDetailService.reEnterChat(user.getUserSeq(), "LEAVE").get(0);
    		chatJoin.setCjStatus("ENTER");
    		chatDetailService.insertJoin(chatJoin);
    	}
        chatJoinId.setCrSeq(chatroom.getCrSeq());
        chatJoinId.setUserSeq(user.getUserSeq());
        
        // ChatJoin 객체 가져오기
        ChatJoin chatjoin = chatDetailService.getChatJoin(chatJoinId);
        
        
        // ChatUser의 필드 설정
        chatUser.setChatJoin(chatjoin);
        chatUser.setCuMessage(chatUser.getCuSender() + "님이 채팅에 참가하셨습니다");
        chatUser.setCuCheck(CheckType.ENTER);
        chatUser.setCuRole(chatUser.getCuRole());
        chatUser.setCuSender(chatUser.getCuSender());
        
        chatDetailService.insertChat(chatUser);


        template.convertAndSend("/sub/chat/room/" +  chatroom.getCrSeq(), chatUser);
    	}
    }
    
    //유저 퇴장
    @MessageMapping("/chat/leaveUser")
    public void leaveUser(@Payload ChatUser chatUser,
    		@Payload ChatRoom chatroom, 
            @Payload User user,
            ChatJoinId chatJoinId
            ) {
    	  // ChatJoinId 객체 설정
    	
    	//입장 문구 체크타입 LEAVE로 변경 (나중에 다시 입장했을 때 다시 입장 문구를 띄우기 위함)
    	List<ChatUser> chatUser1 = chatDetailService.checkEnterUser(user.getUserSeq(), CheckType.ENTER, MessageType.ENTER);
    	ChatUser chatUser2 = chatUser1.get(0);
    	chatUser2.setCuCheck(CheckType.LEAVE);
    	chatDetailService.insertChat(chatUser2);
    
        chatJoinId.setCrSeq(chatroom.getCrSeq());
        chatJoinId.setUserSeq(user.getUserSeq());
        
        // ChatJoin 객체 가져오기
        ChatJoin chatjoin = chatDetailService.getChatJoin(chatJoinId);
        chatjoin.setCjStatus("LEAVE");
        chatDetailService.insertJoin(chatjoin);
        
        // ChatUser의 필드 설정
        chatUser.setChatJoin(chatjoin);
        chatUser.setCuMessage(chatUser.getCuSender() + "님이 퇴장하셨습니다");
        chatUser.setCuCheck(CheckType.LEAVE);
        chatUser.setCuRole(chatUser.getCuRole());
        chatUser.setCuSender(chatUser.getCuSender());
        
        chatDetailService.insertChat(chatUser);
        
        

        template.convertAndSend("/sub/chat/room/" +  chatroom.getCrSeq(), chatUser);
    	
    }
    
    //메세지 전송
    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload ChatUser chatUser, 
                        @Payload ChatRoom chatroom, 
                        @Payload User user,
                        ChatJoinId chatJoinId
                        ) {
 
    // ChatJoinId 객체 설정
    chatJoinId.setCrSeq(chatroom.getCrSeq());
    chatJoinId.setUserSeq(user.getUserSeq());
    
    // ChatJoin 객체 가져오기
    ChatJoin chatjoin = chatDetailService.getChatJoin(chatJoinId);
    
    // ChatUser의 필드 설정
    chatUser.setChatJoin(chatjoin);
    chatUser.setCuMessage(chatUser.getCuMessage());
    chatUser.setCuCheck(CheckType.TALK);
    chatUser.setCuRole(chatUser.getCuRole());
    chatUser.setCuSender(chatUser.getCuSender());
    
    chatDetailService.insertChat(chatUser);
    // 채팅 메시지 전송
    template.convertAndSend("/sub/chat/room/" + chatroom.getCrSeq(), chatUser);
}
    
    @GetMapping("/chat/chatlist")
    @ResponseBody
    public List<ChatUser> chatList(String crSeq) {
        return chatDetailService.getChatUser(crSeq);
    }

    
   

}