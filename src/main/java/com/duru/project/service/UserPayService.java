package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.UserPay;
import com.duru.project.persistence.UserPayRepository;

@Service
public class UserPayService {
	@Autowired
	private UserPayRepository userPayRepository;
	
	@Transactional
	public void insertUserPay(UserPay userPay) {
		userPayRepository.save(userPay);
	}
	
	@Transactional(readOnly = true)
	public List<UserPay> getUserPayList(int userSeq) {
		return userPayRepository.findByUser_UserSeq(userSeq);
	}

}
