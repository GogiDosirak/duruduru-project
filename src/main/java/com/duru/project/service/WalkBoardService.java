package com.duru.project.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.SNSBoard;
import com.duru.project.domain.WalkBoard;
import com.duru.project.persistence.WalkBoardRepository;

@Service
public class WalkBoardService {
	
	@Autowired
	WalkBoardRepository walkBoardRepository;

	@Transactional
	public void insertWalkBoard(WalkBoard walkboard) {
		walkBoardRepository.save(walkboard);
	}
	
	@Transactional(readOnly = true)
	public Page<WalkBoard> getWalkBoardListPage(Pageable pageable){
		return walkBoardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public WalkBoard getWalkBoard(int waboSeq) {
		WalkBoard findWalkBoard = walkBoardRepository.findById(waboSeq).orElseGet(()->{
			return new WalkBoard();
		});
		return findWalkBoard;
	}
	
	@Transactional
	public void deleteWalkBoard(int waboSeq) {
		walkBoardRepository.deleteById(waboSeq);
	}
	
	@Transactional
	public void increaseCnt(WalkBoard walkBoard) {
		walkBoard.setWaboCnt(walkBoard.getWaboCnt()+1);
		walkBoardRepository.save(walkBoard);
	}
	
	//WalkBoardSearchPaging
	@Transactional(readOnly = true)
	public Page<WalkBoard> getSearchWalkBoardsOrderByDistanceAndRecentWithPaging(double userLatitude, double userLongitude, String searchKeyword, Pageable pageable){
	    List<WalkBoard> searchWalkBoard = walkBoardRepository.findBywaboContentContaining(searchKeyword);

	    // 거리와 최근성에 따라 정렬
	    Collections.sort(searchWalkBoard, new DistanceAndRecentComparator(userLatitude, userLongitude));

	    // 5키로 이내의 게시글만 필터링 및 페이징 처리
	    List<WalkBoard> pagedSearchWalkBoard = filterAndPageByDistance(searchWalkBoard, userLatitude, userLongitude, 5.0, pageable.getPageNumber(), pageable.getPageSize());

	    // 전체 리스트에서 페이징된 부분과 전체 개수를 사용하여 Page 객체 생성
	    return new PageImpl<>(pagedSearchWalkBoard, pageable, pagedSearchWalkBoard.size());
	}
	
	//WalkBoardPaging
	@Transactional
	public Page<WalkBoard> getWalkBoardsOrderByDistanceAndRecentWithPaging(double userLatitude, double userLongitude, Pageable pageable) {
	    List<WalkBoard> walkBoard = walkBoardRepository.findAll();

	    // 거리와 최근성에 따라 정렬
	    Collections.sort(walkBoard, new DistanceAndRecentComparator(userLatitude, userLongitude));

	    // 5키로 이내의 게시글만 필터링 및 페이징 처리
	    List<WalkBoard> pagedWalkBoards = filterAndPageByDistance(walkBoard, userLatitude, userLongitude, 5.0, pageable.getPageNumber(), pageable.getPageSize());

	    // 전체 리스트에서 페이징된 부분과 전체 개수를 사용하여 Page 객체 생성
	    return new PageImpl<>(pagedWalkBoards, pageable, pagedWalkBoards.size());
	}
	
	// 범위 이내 확인 및 페이징 처리 메서드
	private List<WalkBoard> filterAndPageByDistance(List<WalkBoard> walkBoards, double userLatitude, double userLongitude, double maxDistance, int page, int pageSize) {
	    // 필터링된 게시글 리스트 생성
	    List<WalkBoard> filteredWalkBoards = walkBoards.stream()
	            .filter(board -> calculateDistance(userLatitude, userLongitude, board.getUser().getLatitude(), board.getUser().getLongitude()) <= maxDistance)
	            .collect(Collectors.toList());

	    // 전체 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) filteredWalkBoards.size() / (double) pageSize);

	    // 페이지가 빈 페이지인지 확인하고, 비어 있다면 빈 리스트를 반환
	    if (page >= totalPages) {
	        return Collections.emptyList();
	    }

	    // 페이징 처리된 결과를 반환
	    int start = page * pageSize;
	    int end = Math.min((page + 1) * pageSize, filteredWalkBoards.size());
	    return filteredWalkBoards.subList(start, end);
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

	//정렬식
	private class DistanceAndRecentComparator implements Comparator<WalkBoard> {
	    private final double userLatitude;
	    private final double userLongitude;

	    public DistanceAndRecentComparator(double userLatitude, double userLongitude) {
	        this.userLatitude = userLatitude;
	        this.userLongitude = userLongitude;
	    }

	    @Override
	    public int compare(WalkBoard board1, WalkBoard board2) {
	        double distance1 = calculateDistance(userLatitude, userLongitude, board1.getUser().getLatitude(), board1.getUser().getLongitude());
	        double distance2 = calculateDistance(userLatitude, userLongitude, board2.getUser().getLatitude(), board2.getUser().getLongitude());

	        int distanceComparison = Double.compare(distance1, distance2);

	        if (distanceComparison == 0) {
	            return Integer.compare(board2.getWaboSeq(), board1.getWaboSeq());
	        }

	        return distanceComparison;
	    }
	}

}
