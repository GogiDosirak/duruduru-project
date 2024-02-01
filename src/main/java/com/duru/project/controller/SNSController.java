package com.duru.project.controller;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.LikeBoard;
import com.duru.project.domain.SNSBoard;
import com.duru.project.domain.SNSBoardComment;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.SNSBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SNSController {

	@Autowired
	SNSBoardService snsBoardService;

	
	
	@GetMapping("/sns")
	public String sns(Model model) {
		List<SNSBoard> SNSList = snsBoardService.SNSList();
		Collections.sort(SNSList, Comparator.comparingInt(SNSBoard::getSnsboSeq).reversed());
		model.addAttribute("SNSList", SNSList);
		
		//댓글
		model.addAttribute("snsCommentList", snsBoardService.getSnsCommentList());
		
		//좋아요
		model.addAttribute("likeList", snsBoardService.likeSns());
		
		//좋아요순리스트
		model.addAttribute("likeCntList", snsBoardService.getTopSNSBoardsByLikes());
		
		return "board/sns/sns";
	}

	@GetMapping("/insertSNS")
	public String insertSNS() {
		return "/board/sns/insertSns";
	}

	@PostMapping("/insertSNS")
	public String insertSNS(SNSBoard snsBoard, MultipartFile file, HttpSession session) throws Exception {
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로를 지정

		UUID uuid = UUID.randomUUID(); // 식별자(이름) 랜덤 생성

		String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙은 다음에 _원래파일이름(매개변수로 들어온)

		File saveFile = new File(projectPath, fileName); // 파일 껍데기를 생성해줄건데 projectPath 경로에 넣어줄거고 이름은 위의 파일네임 (매개변수 file을
															// 넣어줄 껍데기 생성)

		file.transferTo(saveFile); // Exception 해줘야 밑줄 사라짐

		snsBoard.setFilename(fileName); // 저장된 파일의 이름
		snsBoard.setFilepath("/files/" + fileName); // 저장된 파일의 경로와 이름 set

		User findUser = (User) session.getAttribute("principal");
		snsBoard.setUser(findUser);
		snsBoardService.insertSNS(snsBoard, file); // service에서 매개변수 추가됐으므로 file 해주고
		return "redirect:/sns";
	}
	
	
	

	// updatePet11
	@GetMapping("/updateSns/{snsboSeq}")
	public String updateSns(@PathVariable int snsboSeq, Model model) {

		model.addAttribute("sns", snsBoardService.getSNSBaord(snsboSeq));
		return "/board/sns/updateSns";
	}
	
	
	
	

	// updatePet22
	@PostMapping("/updateSns/{snsboSeq}")
	public String updateSns(@PathVariable int snsboSeq, SNSBoard snsBoard, MultipartFile file) throws Exception {
		System.out.println(snsBoard.getSnsboContent());
		SNSBoard findSns = snsBoardService.getSNSBaord(snsboSeq);
		System.out.println(snsboSeq);
		if (file.getSize() != 0) {
			String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectPath, fileName);
			file.transferTo(saveFile);
			findSns.setFilename(fileName);
			findSns.setFilepath("/files/" + fileName);
		}
		findSns.setSnsboContent(snsBoard.getSnsboContent());
		snsBoardService.insertSNS(findSns, file);
		return "redirect:/sns";
	}
	
	
	
	@DeleteMapping("/deleteSns/{snsboSeq}")
	public @ResponseBody ResponseDTO<?> deleteSns (@PathVariable int snsboSeq ) {
		snsBoardService.deleteAllSnsComment(snsboSeq);
		snsBoardService.deleteSNS(snsboSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "삭제가 완료되었습니다.");
	}
	
	
	
	
	//댓글
	
	
	@PostMapping("/insertSnsComment/{snsboSeq}")
	public @ResponseBody ResponseDTO<?> insertSnsComment(@PathVariable int snsboSeq, @RequestBody SNSBoardComment snsBoardComment, HttpSession session ){
		User findUser = (User) session.getAttribute("principal");
		snsBoardComment.setUser(findUser);
		
		SNSBoard findSnsBoard = snsBoardService.getSNSBaord(snsboSeq);
		snsBoardComment.setSnsBoard(findSnsBoard);
		
		snsBoardService.insertSnsComment(snsBoardComment);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글등록이 완료되었습니다.");
		
		
	}
	
	
	
	@DeleteMapping("/deleteSnsComment/{snsboCoSeq}")
	public @ResponseBody ResponseDTO<?> deleteSnsComment(@PathVariable int snsboCoSeq) {
		snsBoardService.deleteSnsComment(snsboCoSeq);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "댓글삭제가 완료되었습니다.");
	}
	
	
	
	//좋아요
	
	
	@PostMapping("/insertSnsLike/{snsboSeq}")
	public @ResponseBody ResponseDTO<?> insertLike(@PathVariable int snsboSeq, LikeBoard likeBoard, HttpSession session) {
		System.out.println("좋앙요" +snsboSeq);
		
		User findUser = (User)session.getAttribute("principal");
		likeBoard.setUser(findUser);
		
		SNSBoard findSnsBoard = snsBoardService.getSNSBaord(snsboSeq);
		likeBoard.setSnsBoard(findSnsBoard);
		
		findSnsBoard.setLikeCnt(findSnsBoard.getLikeCnt()+1);
		
		snsBoardService.insertLike(likeBoard);
		snsBoardService.insertSNS(findSnsBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "좋아요가 눌렸습니다.");
	}
	
	
	@DeleteMapping("/deleteSnsLike/{liboSeq}/{snsboSeq}")
	public @ResponseBody ResponseDTO<?> deleteLike(@PathVariable int liboSeq, @PathVariable int snsboSeq) {
		System.out.println("좋아요 취소"  + liboSeq);
		SNSBoard findSnsBoard = snsBoardService.getSNSBaord(snsboSeq);
		findSnsBoard.setLikeCnt(findSnsBoard.getLikeCnt()-1);
		snsBoardService.deleteLike(liboSeq);
		snsBoardService.insertSNS(findSnsBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "좋아요가 취소되었습니다.");
	}

}
