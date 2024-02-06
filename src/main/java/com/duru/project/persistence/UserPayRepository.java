package com.duru.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.UserPay;

@Repository
public interface UserPayRepository extends JpaRepository<UserPay, Integer> {
	
	List<UserPay> findByUser_UserSeq(int UserSeq); 

}
