package com.duru.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duru.project.domain.ChatJoin;
import com.duru.project.domain.ChatJoinId;
import com.duru.project.domain.ChatRoom;
import com.duru.project.domain.User;
import com.duru.project.service.ChatDetailService;
import com.duru.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatRoomController {

	@Autowired
	private ChatDetailService chatDetailService;

	@Autowired
	private UserService userService;

	@GetMapping("/chat")
	public String goChatRoom(HttpSession session) {
		User user = (User) session.getAttribute("principal");
		session.setAttribute("list", chatDetailService.chatRoomList(user.getUserSeq() , "ENTER"));
		return "/chat/roomlist";
	}

	// 방 생성 컨트롤러
	@PostMapping("/chat/createroom")
	public String createRoom(HttpSession session, @RequestParam String crName, @RequestParam int crCount,
			RedirectAttributes rttr, ChatRoom chatroom, ChatJoin chatjoin, ChatJoinId chatJoinId) {
		User user = (User) session.getAttribute("principal");
		UUID uuid = UUID.randomUUID();
		String crSeq = uuid.toString();
		chatroom.setCrSeq(crSeq);
		chatroom.setCrName(crName);
		chatroom.setCrCount(crCount);
		chatDetailService.insertRoom(chatroom);
		chatJoinId.setCrSeq(crSeq);
		chatJoinId.setUserSeq(user.getUserSeq());

		chatjoin.setUser(user);
		chatjoin.setChatRoom(chatroom);
		chatjoin.setId(chatJoinId);
		chatjoin.setCjStatus("ENTER");
		chatDetailService.insertJoin(chatjoin);

		return "redirect:/chat";
	}

	@GetMapping("/chat/room/{crSeq}")
	public String roomDetail(Model model, @PathVariable String crSeq) {
		
		model.addAttribute("user", chatDetailService.chatUserList(crSeq, "ENTER"));	
		model.addAttribute("room", chatDetailService.getChatroom(crSeq));

		return "/chat/chatroom";
	}

	// 방 입장 컨트롤러
	@PostMapping("/chat/chatCode/{chatCode}")
	@ResponseBody
	public boolean chatCode(@PathVariable String chatCode, ChatJoinId chatJoinId, HttpSession session,
			ChatJoin chatjoin) {

		ChatRoom chatRoom = chatDetailService.getChatroom(chatCode);
		List<ChatJoin> chatJoin2 = chatDetailService.getJoinUserCount(chatCode);
		if (chatRoom.getCrCount() == chatJoin2.size()) {
			return false;
		} else {
			chatJoinId.setCrSeq(chatCode);
			User user = (User) session.getAttribute("principal");
			chatJoinId.setUserSeq(user.getUserSeq());

			chatjoin.setChatRoom(chatRoom);
			chatjoin.setUser(user);
			chatjoin.setId(chatJoinId);
			chatjoin.setCjStatus("ENTER");

			chatDetailService.insertJoin(chatjoin);
			return true;
		}
	}

	//1:1 대화방 생성 및 중복체크
	@PostMapping("/chat/onebyone/{userSeq}")
	@ResponseBody
	public String onebyone(HttpSession session, @PathVariable int userSeq, RedirectAttributes rttr, ChatRoom chatroom,
			ChatJoin chatjoin, ChatJoinId chatJoinId) {

		User user = userService.getCheckUser(userSeq);
		User user2 = (User) session.getAttribute("principal");

		String check = chatDetailService.checkOnebyone(userSeq, user2.getUserSeq());
		if (check == null) {

			UUID uuid = UUID.randomUUID();
			String crSeq = uuid.toString();
			chatroom.setCrSeq(crSeq + "g");
			chatroom.setCrName(user.getNickname() + "님과 " + user2.getNickname() + "님의 대화방");
			chatroom.setCrCount(2);
			chatDetailService.insertRoom(chatroom);
			chatJoinId.setCrSeq(crSeq);
			chatJoinId.setUserSeq(user.getUserSeq());

			chatjoin.setUser(user);
			chatjoin.setChatRoom(chatroom);
			chatjoin.setId(chatJoinId);
			chatjoin.setCjStatus("ENTER");
			chatDetailService.insertJoin(chatjoin);

			chatJoinId.setUserSeq(user2.getUserSeq());
			chatjoin.setUser(user2);
			chatDetailService.insertJoin(chatjoin);

			return crSeq + "g";
		}
		return check;
	}

}
