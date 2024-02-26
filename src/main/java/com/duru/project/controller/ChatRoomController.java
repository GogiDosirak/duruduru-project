package com.duru.project.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.User;
import com.duru.project.service.ChatDetailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatRoomController {


    @Autowired 
    private ChatDetailService chatDetailService;

    @GetMapping("/chat")
    public String goChatRoom(Model model){
    	
    	model.addAttribute("list", chatDetailService.chatRoomList());    
    	

        return "/chat/roomlist";
    }
    
    
    @PostMapping("/chat/createroom")
    public String createRoom(HttpSession session, @RequestParam String crName, @RequestParam int crCount, 
    		RedirectAttributes rttr, ChatRoom chatroom, ChatJoin chatjoin, ChatJoinId chatJoinId) {
    	User user = (User)session.getAttribute("principal");
    	UUID uuid = UUID.randomUUID();
    	String crSeq = uuid.toString();
    	System.out.println("crSeq");
    	chatroom.setCrSeq(crSeq);
    	chatroom.setCrName(crName);
    	chatroom.setCrCount(crCount);
    	chatDetailService.insertRoom(chatroom);
    	chatJoinId.setCrSeq(crSeq);
    	chatJoinId.setUserSeq(user.getUserSeq());
    	
    	chatjoin.setUser(user);
    	chatjoin.setChatRoom(chatroom);
    	chatjoin.setId(chatJoinId);
    	chatDetailService.insertJoin(chatjoin);
  
    	
   
        return "redirect:/chat";
    }

    @GetMapping("/chat/room/{crSeq}")
    public String roomDetail(Model model, @PathVariable String crSeq){
    	model.addAttribute("room", chatDetailService.getChatroom(crSeq));
        return "/chat/chatroom";
    }

}
