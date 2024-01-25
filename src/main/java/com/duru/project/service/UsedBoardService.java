package com.duru.project.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.UsedBoard;
import com.duru.project.persistence.UsedBoardRepository;

@Service
public class UsedBoardService {
	
	@Autowired
	UsedBoardRepository usedBoardRepository;
	
	
	
	
	@Transactional
	public void insertUsedBoard(UsedBoard usedBoard, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로를 지정
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

}
