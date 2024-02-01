
package com.duru.project.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duru.project.domain.SNSBoard;
import com.duru.project.persistence.SNSBoardRepository;

//정렬로직은 서비스 클래스에서 구현하는게 좋음
@Service
public class WichiService {

    @Autowired
    SNSBoardRepository snsBoardRepository;

    //1순위 거리, 2순위 최근이 먼저 오도록 구현
    public List<SNSBoard> getBoardsOrderByDistanceAndRecent(Double userLatitude, Double userLongitude) {

        List<SNSBoard> allBoards = snsBoardRepository.findAll();

        // 거리와 최근성에 따라 정렬
        Collections.sort(allBoards, new DistanceAndRecentComparator(userLatitude, userLongitude));

        return allBoards;
    }

    // 내부 클래스로 Comparator를 구현 -> WichiService에서만 사용되는 특정한 Comparator로 사용
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
