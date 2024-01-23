package com.duru.project.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duru.project.dto.ResponseDTO;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.MessageListRequest;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MessageListResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@RestController
public class CoolSMSController {

	
	final DefaultMessageService messageService;

	public CoolSMSController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSBMCTMSRRVNVDI", "ZNVNDLKW9RSWRAU7PDBLAB3O25XJO7GQ", "https://api.coolsms.co.kr");
    }

	/**
	 * 메시지 조회 예제
	 */
	@GetMapping("/get-message-list")
	public MessageListResponse getMessageList() {
		MessageListRequest request = new MessageListRequest();
		return this.messageService.getMessageList(request);
	}

	/**
	 * 단일 메시지 발송 예제
	 */
	@PostMapping("/send-one")
	public SingleMessageSentResponse sendOne(@RequestParam("phonenumber")String phonenumber, HttpSession session) {
		Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		//발신번호
		message.setFrom("01040240519");
		//수신번호
		message.setTo(phonenumber);
		//랜덤인증번호(4)
	    Random rand  = new Random();
	        String numStr = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	    }
	    //랜덤인증번호저장
	    session.setAttribute("numStr", numStr);    
	    //작성된 메세지
		message.setText("인증번호는 "+numStr+"입니다. 정확히 입력해주세요.");

		SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
		System.out.println(response);

		return response;
	}

	/**
	 * 잔액 조회 예제
	 */
	@GetMapping("/get-balance")
	public Balance getBalance() {
		Balance balance = this.messageService.getBalance();
		System.out.println(balance);

		return balance;
	}
	
	@PostMapping("/checkSMS")
	public ResponseDTO<?> checkSMS(@RequestParam("checknumber")String checknumber, HttpSession session){
		String sessionNumStr = (String) session.getAttribute("numStr");
		if(sessionNumStr.equals(checknumber)) {
			return new ResponseDTO<>(HttpStatus.OK.value(),"인증이 완료되었습니다.");			
		}else {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),"번호가 일치하지 않습니다.");
		}
	}

}


