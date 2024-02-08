package com.duru.project.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.LikeBoard;
import com.duru.project.domain.SNSBoard;
import com.duru.project.domain.SNSBoardComment;
import com.duru.project.persistence.SNSBoardRepository;
import com.duru.project.persistence.SNSCommentRepository;
import com.duru.project.persistence.likeRepository;

@Service
public class SNSBoardService {

	@Autowired
	SNSBoardRepository snsBoardRepository;

	@Autowired
	SNSCommentRepository snsCommentRepository;

	@Autowired
	likeRepository likeRepository;

	@Transactional
	public void insertSNS(SNSBoard snsBoard) {
		snsBoardRepository.save(snsBoard);
	}

	@Transactional
	public void insertSNS(SNSBoard snsBoard, MultipartFile file) throws Exception {
		snsBoardRepository.save(snsBoard);
	}

	@Transactional
	public List<SNSBoard> SNSList() {
		return snsBoardRepository.findAll();
	}

	   @Transactional
	   public void deleteSNS(int snsboSeq) {
	      likeRepository.deleteBySnsBoard_snsboSeq(snsboSeq);
	      snsBoardRepository.deleteById(snsboSeq);
	   }

	@Transactional(readOnly = true)
	public SNSBoard getSNSBaord(int snsboSeq) {

		SNSBoard findSns = snsBoardRepository.findById(snsboSeq).orElseGet(() -> {
			return new SNSBoard();
		});
		return findSns;

	}

	// 댓글

	@Transactional
	public void insertSnsComment(SNSBoardComment snsBoardComment) {
		snsCommentRepository.save(snsBoardComment);
	}

	@Transactional(readOnly = true)
	public List<SNSBoardComment> getSnsCommentList() {
		return snsCommentRepository.findAll();
	}

	@Transactional
	public void deleteSnsComment(int snsboCoSeq) {
		snsCommentRepository.deleteById(snsboCoSeq);
	}

	@Transactional
	public void deleteAllSnsComment(int snsboSeq) {
		snsCommentRepository.deleteBySnsBoard_snsboSeq(snsboSeq);
	}

	// 좋아요

	// 좋아요 개수
	@Transactional(readOnly = true)
	public List<LikeBoard> likeSns() {
		return likeRepository.findAll();

	}

	// 좋아요 하기
	@Transactional
	public void insertLike(LikeBoard likeBoard) {
		likeRepository.save(likeBoard);
	}

	// 좋아요 취소
	@Transactional
	public void deleteLike(int liboSeq) {
		likeRepository.deleteById(liboSeq);
	}

	//좋아요순 정렬
	@Transactional
	public List<SNSBoard> getTopSNSBoardsByLikes() {
		return snsBoardRepository.findAllByOrderByLikeCntDesc();
	}
	
    //1순위 거리, 2순위 최근이 먼저 오도록 구현
	@Transactional
    public List<SNSBoard> getSNSBoardsOrderByDistanceAndRecent(Double userLatitude, Double userLongitude) {

        List<SNSBoard> snsBoards = snsBoardRepository.findAll();

        // 거리와 최근성에 따라 정렬
        Collections.sort(snsBoards, new DistanceAndRecentComparator(userLatitude, userLongitude));

        return snsBoards;
    }

    // 내부 클래스로 Comparator를 구현 -> SNSService에서만 사용되는 특정한 Comparator로 사용
    private static class DistanceAndRecentComparator implements Comparator<SNSBoard> {
        private final Double userLatitude;
        private final Double userLongitude;
        //지구 반지름(Haversine 공식에서 사용)
        private static final int R = 6371; // 상수로 선언

        public DistanceAndRecentComparator(Double userLatitude, Double userLongitude) {
            this.userLatitude = userLatitude;
            this.userLongitude = userLongitude;
        }

        //Comparator 인터페이스의 compare 메서드를 오버라이드
        //Comparator는 두 객체를 비교하는 데 사용되는 인터페이스
        //compare 메서드는 두 객체를 비교하는 로직을 정의할 때 사용
        @Override
        public int compare(SNSBoard board1, SNSBoard board2) {
            
        	//1순위 거리 비교(calculateDistance는 아래서 정의)
        	double distance1 = calculateDistance(userLatitude, userLongitude, board1.getUser().getLatitude(), board1.getUser().getLongitude());
            double distance2 = calculateDistance(userLatitude, userLongitude, board2.getUser().getLatitude(), board2.getUser().getLongitude());
			
            //두 객체간의 거리 정의
            int distanceComparison = Double.compare(distance1, distance2);

            //2순위 거리가 같을 때 최근성 비교
            if (distanceComparison == 0) {
                return board2.getSnsboDate().compareTo(board1.getSnsboDate());
            }

            return distanceComparison;
        }

        //두 지점간의 위도 경도를 가지고 Haversine 공식으로 거리 계산
        //위도 : lat , 경도 : lon
        private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                    * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return R * c;
        }
    }

}
