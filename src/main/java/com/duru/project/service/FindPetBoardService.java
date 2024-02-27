package com.duru.project.service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.duru.project.domain.FindPetBoard;
import com.duru.project.persistence.FindPetBoardRepository;

@Service
public class FindPetBoardService {
	
	@Autowired
	private FindPetBoardRepository findPetBoardRepository;
	

	@Transactional
	public void insertFindPetBoard(FindPetBoard findPetBoard, MultipartFile file) throws Exception { 
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; 
		UUID uuid = UUID.randomUUID(); 
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		File saveFile = new File(projectPath, fileName); 
		file.transferTo(saveFile); 
		
		

		findPetBoard.setFpFilename(fileName);  
		findPetBoard.setFpFilepath("/files/"+fileName);  
		
		findPetBoardRepository.save(findPetBoard);

	}
	
	
	@Transactional(readOnly = true)
	public FindPetBoard getFindPetBoard(int fpSeq) {
		FindPetBoard findFindPetBoard = findPetBoardRepository.findById(fpSeq).orElseGet( () -> {
			return new FindPetBoard();
		});
		return findFindPetBoard;
		
	}
	
	
	
	@Transactional
	public void updateFindPetBoard(int fpSeq, FindPetBoard findPetBoard, MultipartFile file) throws Exception {
		FindPetBoard findFindPetBoard = findPetBoardRepository.findById(fpSeq).get(); 		
		if(file.getSize() != 0) { 
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; 
		UUID uuid = UUID.randomUUID(); 
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		File saveFile = new File(projectPath, fileName); 
		file.transferTo(saveFile);
		
		findPetBoard.setFpFilename(fileName);
		findPetBoard.setFpFilepath("/files/" + fileName);
		findFindPetBoard.setFpFilename(findPetBoard.getFpFilename());
		findFindPetBoard.setFpFilepath(findPetBoard.getFpFilepath()); 
		
		findFindPetBoard.setFpFilename(fileName);
	}
		
		
		findFindPetBoard.setFpTitle(findPetBoard.getFpTitle());  
		findFindPetBoard.setFpContent(findPetBoard.getFpContent());  
		
		
		findPetBoardRepository.save(findFindPetBoard); 
	};
	
	
	
	@Transactional(readOnly = true)
	public Page<FindPetBoard> boardTitleSearchList(String searchKeyword, Pageable pageable) {
		return findPetBoardRepository.findByFpTitleContaining(searchKeyword, pageable);
	}
	
	
	@Transactional
	public Page<FindPetBoard> usedAll(Pageable pageable) {
		return findPetBoardRepository.findAll(pageable);
	}


	
	
	@Transactional
	public void deleteFindPetBoard(int fpSeq) {
		findPetBoardRepository.deleteById(fpSeq);
	}
	
	//조회수
		@Transactional
		public void increacCnt(FindPetBoard findPetBoard) {
			findPetBoard.setFpCnt(findPetBoard.getFpCnt() + 1);
			findPetBoardRepository.save(findPetBoard);
		}
		
		

		
		

		
		
		
			@Transactional
			public Page<FindPetBoard> getFindPetBoardOrderByDistance(Double userLatitude, Double userLongitude, Pageable pageable) {
			    Page<FindPetBoard> findPetBoardsPage = findPetBoardRepository.getFindPetBoardOrderByDistance(userLongitude, userLatitude, pageable);
			    return filterFindPetBoardsByDistance(findPetBoardsPage, userLatitude, userLongitude);
			}

			
			@Transactional
			public Page<FindPetBoard> searchFPetBoardByKeywordAndOrderByDistance(String searchKeyword, Double userLatitude, Double userLongitude, Pageable pageable) {
			    Page<FindPetBoard> findPetBoardsPage = findPetBoardRepository.searchFindPetBoardByKeywordAndOrderByDistance(searchKeyword, userLongitude, userLatitude, pageable);
			    return filterFindPetBoardsByDistance(findPetBoardsPage, userLatitude, userLongitude);
			}
				
				

				
			
		
			private Page<FindPetBoard> filterFindPetBoardsByDistance(Page<FindPetBoard> findPetBoards, Double userLongitude , Double userLatitude) {
			    List<FindPetBoard> filteredList = new ArrayList<>();
			    for (FindPetBoard fPetBoard : findPetBoards) {
			        double distance = calculateDistance( userLongitude,userLatitude, fPetBoard.getUser().getLongitude(), fPetBoard.getUser().getLatitude());
			        if (distance <= 5) {
			            filteredList.add(fPetBoard);
			        }
			    }
			    
			    return new PageImpl<>(filteredList, findPetBoards.getPageable(), filteredList.size());
			}
			
			
			
		



		
		
		//5km이내 거리 계산
		private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		    // 지구의 반지름 (단위: km)
		    final double R = 6371.0;

		    // 위도 및 경도를 라디안 단위로 변환
		    double lat1Rad = Math.toRadians(lat1);
		    double lon1Rad = Math.toRadians(lon1);
		    double lat2Rad = Math.toRadians(lat2);
		    double lon2Rad = Math.toRadians(lon2);


		    // 위도 차이와 경도 차이 계산
		    double deltaLat = lat2Rad - lat1Rad;
		    double deltaLon = lon2Rad - lon1Rad;

		    // Haversine 공식을 사용하여 두 지점 사이의 거리를 계산
		    double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(deltaLon / 2), 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c;

		    return distance;
		}

	
	
	
	
	
	
}
