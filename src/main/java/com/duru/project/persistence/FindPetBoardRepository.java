package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duru.project.domain.FindPetBoard;
import com.duru.project.domain.UsedBoard;

public interface FindPetBoardRepository extends JpaRepository<FindPetBoard, Integer>{
	
	//관리자인 경우 모두 볼 수 있게
	Page<FindPetBoard> findByFpTitleContaining(String searchKeyword, Pageable pageable);

	//검색 null일 때ㅔ 나머지 리스트 다 나오게(관리자인 경우 모두 볼 수 있게)
	Page<FindPetBoard> findAll(Pageable pageable);

	
	
	//5km이내 거리 가까운 순, 거리 똑같으면 seq순으로 
		@Query(value = "SELECT fb FROM FindPetBoard fb ORDER BY ST_DISTANCE_SPHERE(POINT(:userLongitude, :userLatitude), POINT(fb.user.longitude, fb.user.latitude)) ASC, fb.fpSeq DESC")
		Page<FindPetBoard> getFindPetBoardOrderByDistance(@Param("userLatitude") double userLatitude,
				@Param("userLongitude") double userLongitude, Pageable pageable);

		
		
		//5km이내 거리 가까운 순, 거리 똑같으면 seq순으로 (검색까지)
		@Query(value = "SELECT fb FROM FindPetBoard fb WHERE "
				+ "LOWER(fb.fpTitle) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) "
				+ "ORDER BY ST_DISTANCE_SPHERE(POINT(:userLongitude, :userLatitude), POINT(fb.user.longitude, fb.user.latitude)) ASC, fb.fpSeq DESC")
		Page<FindPetBoard> searchFindPetBoardByKeywordAndOrderByDistance(@Param("searchKeyword") String searchKeyword,
				@Param("userLatitude") double userLatitude, @Param("userLongitude") double userLongitude,
				Pageable pageable);

}
