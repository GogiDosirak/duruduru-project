package com.duru.project.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	

	@PostMapping("/login")
	public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUserid());

		if (findUser.getUserid() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디가 존재하지 않습니다.");
		} else {
			if (user.getPassword().equals(findUser.getPassword())) {
				LocalDate today = LocalDate.now();
				if (findUser.getLastPointDate() == null || !findUser.getLastPointDate().equals(today)) {
					session.setAttribute("principal", findUser);

					// 포인트 증가 로직 추가
					int pointToAdd = 10; // 포인트를 증가시킬 값
					findUser.setPoint(findUser.getPoint() + pointToAdd);
					findUser.setLastPointDate(today); // 마지막 포인트 쌓은 날짜 업데이트
					userService.saveUser(findUser); // 변경된 포인트와 날짜를 저장

					return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUserid() + "님이 로그인하였습니다.");
				} else {
					return new ResponseDTO<>(HttpStatus.OK.value(), "하루에 한 번만 포인트를 쌓을 수 있습니다.");
				}
			} else {
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호가 틀렸습니다.");
			}
		}
	}

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}

	@PostMapping("/join")
	public @ResponseBody ResponseDTO<?> insertUser(@RequestBody User user) {
		User findUser = userService.getUser(user.getUserid());
		if (findUser.getUserid() == null) {
			userService.insertUser(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUserid() + "님 회원가입 완료되었습니다.");
		} else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUserid() + "은 중복된 아이디 입니다.");
		}
	}
	
	
	

	@GetMapping("/findid")
	public String findid() {
		return "user/findid";
	}

	
	
	@PostMapping("/findid")
	public @ResponseBody ResponseDTO<?> findid(@RequestBody User user) {
		System.out.println(user.getPhonenumber());
		User findUser = userService.getUserid(user.getEmail(), user.getPhonenumber());
		
		if(findUser.getUserid() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디가 존재하지 않습니다.");
		} else {
				return new ResponseDTO<>(HttpStatus.OK.value(), "회원님의 아이디는 " + findUser.getUserid() + " 입니다.");	
		}
	}
	
	@PostMapping("/findpw")
	public @ResponseBody ResponseDTO<?> findpw(@RequestBody User user) {
		User findUser = userService.getUserpw(user.getUserid(), user.getPhonenumber(), user.getEmail());
		System.out.println(findUser.getPhonenumber());
		System.out.println(findUser.getUserid());
		System.out.println(findUser.getEmail());
		System.out.println(findUser.getPassword());
		if(findUser.getPassword() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호가 존재하지 않습니다.");
		} else {
				return new ResponseDTO<>(HttpStatus.OK.value(), "회원님의 비밀번호는 " + findUser.getPassword() + " 입니다.");
		}
	}
	
	@PostMapping("/checkId")
	   public @ResponseBody ResponseDTO<?> checkId(@RequestBody User user){
	      User findUser = userService.getUser(user.getUserid());
	      if (findUser.getUserid() == null) {
	         return new ResponseDTO<>(HttpStatus.OK.value(), "회원가입 가능합니다.");
	      } else {
	         return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), findUser.getUserid()+"는 중복된 아이디 입니다.");
	      }
	   }
	   
	   
	   @PostMapping("/checkPw")
	   public @ResponseBody ResponseDTO<?> checkPw(String password, String password2){
	      if(password.equals(password2)) {
	         return new ResponseDTO<>(HttpStatus.OK.value(), "번호가 일치합니다.");
	      }return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "번호가 일치하지 않습니다.");
	   }
	   
	   @GetMapping("/mypage")
		public String mypage() {
			return "user/mypage";
		}
		
		@PutMapping("/updateUser/{user_seq}")
		public @ResponseBody ResponseDTO<?> updateUser(@RequestBody User user, @PathVariable int user_seq, HttpSession session) {
			User myUser = (User)session.getAttribute("principal");
			myUser.setPassword(user.getPassword());
			myUser.setNickname(user.getNickname());
			userService.updateUser(myUser, session);
			return new ResponseDTO<>(HttpStatus.OK.value(),"회원 수정이 성공했습니다.");
			
		}
}
