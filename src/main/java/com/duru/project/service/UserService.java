package com.duru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.RoleType;
import com.duru.project.domain.User;
import com.duru.project.persistence.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;
	
	
	@Transactional(readOnly = true)
	public User getUser (String userid) {
		User findUser = userRepository.findByUserid(userid).orElseGet( () -> {
			return new User();
		});
		return findUser;
	}
	
	
	
	@Transactional
	public void insertUser(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	
	@Transactional
	public void saveUser(User user) {
	    userRepository.save(user);
	}
	
	
	
	@Transactional(readOnly = true)
	public User getUserid (String email, String phonenumber) {
		User findUser = userRepository.findByEmailAndPhonenumber(email, phonenumber).orElseGet( () -> {
			return new User();
		});
		return findUser;
	}
	
	@Transactional(readOnly = true)
	public User getUserpw (String userid, String phonenumber2, String email2) {
		User findUser = userRepository.findByUseridAndPhonenumberAndEmail(userid , phonenumber2, email2).orElseGet( () -> {
			return new User();
		});
		return findUser;
		
	}
	
	@Transactional
	public void updateUser(User user, HttpSession session) {
		User findUser = (User) session.getAttribute("principal");
		findUser.setPassword(user.getPassword());
		findUser.setNickname(user.getNickname());
		userRepository.save(findUser);
		
		
	}
	
	@Transactional(readOnly = true)
	public User getCheckUser (int userSeq) {
		User findUser = userRepository.findById(userSeq).get();
		return findUser;
		
	}
	
	@Transactional
	public void updateCheckUser(User user) {
		userRepository.save(user);
		
		
	}
	

}
