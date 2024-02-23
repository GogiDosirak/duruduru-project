package com.duru.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.WalkReservation;
import com.duru.project.persistence.WalkReservationRepository;

@Service
public class WalkReservationService {
	
	@Autowired
	private WalkReservationRepository walkReservationRepository;
	
	@Transactional
	public void insertWalkReservation(WalkReservation WalkReservation) {
		walkReservationRepository.save(WalkReservation);
	}
	
	@Transactional(readOnly = true)
	public Page<WalkReservation> getWalkReservationListPage(Pageable pageable){
		return walkReservationRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public WalkReservation getWalkReservation(int waboSeq) {
		WalkReservation findWalkReservation = walkReservationRepository.findById(waboSeq).orElseGet(()->{
			return new WalkReservation();
		});
		return findWalkReservation;
	}
	
	@Transactional
	public void deleteWalkReservation(int waboSeq) {
		walkReservationRepository.deleteById(waboSeq);
	}
	
	@Transactional
	public void increaseCnt(WalkReservation walkReservation) {
		walkReservation.setWareCnt(walkReservation.getWareCnt()+1);
		walkReservationRepository.save(walkReservation);
	}
	
	//WalkReservationSearchPaging
	@Transactional(readOnly = true)
	public Page<WalkReservation> getWalkReservationsOrderByDistanceAndRecentWithPaging(double userLatitude, double userLongitude, String searchKeyword, Pageable pageable) {
	    List<WalkReservation> WalkReservationList = null;

	    //검색어가 있는지 없는지 확인
	    if (searchKeyword != null && !searchKeyword.isEmpty()) {
	        WalkReservationList = walkReservationRepository.findBywareTitleContaining(searchKeyword);
	    } else {
	        WalkReservationList = walkReservationRepository.findAll();
	    }

	    // 거리와 최근성에 따라 정렬
	    Collections.sort(WalkReservationList, new DistanceAndRecentComparator(userLatitude, userLongitude));

	    // 필터링된 게시글 리스트 생성
	    List<WalkReservation> filteredWalkReservations = new ArrayList<>();
	    for (WalkReservation board : WalkReservationList) {
	        double distance = calculateDistance(userLatitude, userLongitude, board.getUser().getLatitude(), board.getUser().getLongitude());
	        if (distance <= 5.0) {
	            filteredWalkReservations.add(board);
	        }
	    }

	    // 페이지 계산
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), filteredWalkReservations.size());
	    List<WalkReservation> pagedWalkReservations = filteredWalkReservations.subList(start, end);

	    // 페이지 객체 생성
	    return new PageImpl<>(pagedWalkReservations, pageable, filteredWalkReservations.size());
	}

	//정렬식
	private class DistanceAndRecentComparator implements Comparator<WalkReservation> {
	    private final double userLatitude;
	    private final double userLongitude;

	    public DistanceAndRecentComparator(double userLatitude, double userLongitude) {
	        this.userLatitude = userLatitude;
	        this.userLongitude = userLongitude;
	    }

	    @Override
	    public int compare(WalkReservation board1, WalkReservation board2) {
	        double distance1 = calculateDistance(userLatitude, userLongitude, board1.getUser().getLatitude(), board1.getUser().getLongitude());
	        double distance2 = calculateDistance(userLatitude, userLongitude, board2.getUser().getLatitude(), board2.getUser().getLongitude());

	        int distanceComparison = Double.compare(distance1, distance2);

	        if (distanceComparison == 0) {
	            return Integer.compare(board2.getWareSeq(), board1.getWareSeq());
	        }

	        return distanceComparison;
	    }
	}
	
	// 위도 경도 비교 공식
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);

	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    final int R = 6371;
	    return R * c;
	}

}
