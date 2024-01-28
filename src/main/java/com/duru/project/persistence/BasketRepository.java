package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
	
	// 자기 자신이 추가한 상품만 보여야하기 떄문에
	List<Basket> findByUser_UserSeq(int userSeq); // 연결된 User테이블의 UserSeq로 검색
	Basket findByProduct_ProductSeq(int productSeq);
	void deleteByProduct_ProductSeq(int productSeq);

}
