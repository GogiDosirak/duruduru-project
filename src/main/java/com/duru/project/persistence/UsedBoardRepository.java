package com.duru.project.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.UsedBoard;

@Repository
public interface UsedBoardRepository extends JpaRepository<UsedBoard, Integer> {

	//관리자인 경우 모두 볼 수 있게
	Page<UsedBoard> findByUsboTitleContaining(String searchKeyword, Pageable pageable);

	//검색 null일 때ㅔ 나머지 리스트 다 나오게(관리자인 경우 모두 볼 수 있게)
	Page<UsedBoard> findAll(Pageable pageable);

	
	
	
	 // 거리 기준 정렬만 하는 메서드
	@Query(value = "SELECT ub FROM UsedBoard ub ORDER BY ST_DISTANCE_SPHERE(POINT(:userLatitude, :userLongitude), POINT(ub.user.latitude, ub.user.longitude)) ASC, ub.usboSeq DESC")
	Page<UsedBoard> getUsedBoardOrderByDistance(@Param("userLatitude") double userLatitude, 
												@Param("userLongitude") double userLongitude, Pageable pageable);
	
	

    // 거리 기준 정렬 및 키워드 검색 지원 메서드
    @Query(value = "SELECT ub FROM UsedBoard ub WHERE " +
            "LOWER(ub.usboTitle) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) " +
            "ORDER BY ST_DISTANCE_SPHERE(POINT(:userLatitude, :userLongitude), POINT(ub.user.latitude, ub.user.longitude)) ASC, ub.usboSeq DESC")
    Page<UsedBoard> searchUsedBoardByKeywordAndOrderByDistance(@Param("searchKeyword") String searchKeyword, 
    														@Param("userLatitude")double userLatitude, @Param("userLongitude")double userLongitude, Pageable pageable);

	
	
	
	


}
