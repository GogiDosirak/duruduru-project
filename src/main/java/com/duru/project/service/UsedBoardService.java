package com.duru.project.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.UsedBoard;
import com.duru.project.domain.UsedComment;
import com.duru.project.persistence.UsedBoardRepository;
import com.duru.project.persistence.UsedCommentRepository;

@Service
public class UsedBoardService {
	
	@Autowired
	UsedBoardRepository usedBoardRepository;
	
	@Autowired
	UsedCommentRepository usedCommentRepository;
	
	
	
	
	@Transactional
	public void insertUsedBoard(UsedBoard usedBoard, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; // 저장할 경로를 지정
		UUID uuid = UUID.randomUUID(); // 식별자(이름) 랜덤 생성
		String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙은 다음에 _원래파일이름(매개변수로 들어온)
		File saveFile = new File(projectPath, fileName); // 파일 껍데기를 생성해줄건데 projectPath 경로에 넣어줄거고 이름은 위의 파일네임 (매개변수 file을 넣어줄 껍데기 생성)
		file.transferTo(saveFile); // Exception 해줘야 밑줄 사라짐
		
		

		usedBoard.setUsedFilename(fileName);  //저장된 파일의 이름
		usedBoard.setUsedFilepath("/files/"+fileName);  //저장된 파일의 경로와 이름 set
		
		usedBoardRepository.save(usedBoard);

	}
	
	
	@Transactional(readOnly = true)
	public Page<UsedBoard> usedBoardList (Pageable pageable) {
		return usedBoardRepository.findAll(pageable);
	}

	
	
	@Transactional(readOnly = true)
	public UsedBoard getUsedBoard(int usboSeq) {
		UsedBoard findUsedBoard = usedBoardRepository.findById(usboSeq).orElseGet( () -> {
			return new UsedBoard();
		});
		return findUsedBoard;
		
	}
	
	
	
	@Transactional
	public void updateUsedBoard(int usboSeq, UsedBoard usedBoard, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
		UsedBoard findUsedBoard = usedBoardRepository.findById(usboSeq).get(); //seq를 받아서 맞는 상품을 찾은 뒤 저장		
		if(file.getSize() != 0) { //파일 사이즈가 0이 아닌경우 (즉, 썸네일이 새로 업로드 된 경우에만)
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; 
		UUID uuid = UUID.randomUUID(); 
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		File saveFile = new File(projectPath, fileName); 
		file.transferTo(saveFile);
		
		usedBoard.setUsedFilename(fileName);
		usedBoard.setUsedFilepath("/files/" + fileName);
		findUsedBoard.setUsedFilename(usedBoard.getUsedFilename());
		findUsedBoard.setUsedFilepath(usedBoard.getUsedFilepath()); // 파일패스와 파일네임을 새로 지정해주고
		
		findUsedBoard.setUsedFilename(fileName);
	}
		
		
		findUsedBoard.setUsboTitle(usedBoard.getUsboTitle());  //아닌 경우엔 그냥 원래 파일네임,경로 사용
		findUsedBoard.setUsboContent(usedBoard.getUsboContent());  //수정된 값들 받아서 저장!
		
		
		usedBoardRepository.save(findUsedBoard); //최종적으로 Repository에 저장
	}
	
	
	@Transactional(readOnly = true)
	public Page<UsedBoard> boardTitleSearchList(String searchKeyword, Pageable pageable) {
		return usedBoardRepository.findByUsboTitleContaining(searchKeyword, pageable);
	}
	
	
	
	@Transactional
	public void deleteUsedBoard(int usboSeq) {
		usedBoardRepository.deleteById(usboSeq);
	}
	
	
	
	
	
	
	//댓글 
	
	
	@Transactional
	public void insertUsedComment(UsedComment usedComment) {
		usedCommentRepository.save(usedComment);
	}
	
	
	@Transactional(readOnly = true)
	public List<UsedComment> usedCommentList() {
		return usedCommentRepository.findAll();
	}
	
	
	
	@Transactional
	public void deleteUsedComment(int usboCoSeq) {
		usedCommentRepository.deleteById(usboCoSeq);
	}
	
	
	@Transactional
	public void deleteAllUsedComment(int usboSeq) {
		usedCommentRepository.deleteByUsedBoard_usboSeq(usboSeq);
	}
	
	
	//조회수
	@Transactional
	public void increacCnt(UsedBoard usedBoard) {
		usedBoard.setUsboCnt(usedBoard.getUsboCnt() + 1);
		usedBoardRepository.save(usedBoard);
	}
	
	
	
}
