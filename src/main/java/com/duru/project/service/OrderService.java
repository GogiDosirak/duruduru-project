package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.UserOrder;
import com.duru.project.persistence.OrderRepository;


@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public void insertOrder(UserOrder userOrder) {
		orderRepository.save(userOrder);
	}
	
	@Transactional
	public void deleteOrder(int orderSeq) {
		orderRepository.deleteById(orderSeq);
	}
	
	@Transactional(readOnly = true)
	public List<UserOrder> getOrderList(int userSeq) {
		return orderRepository.findByUser_UserSeq(userSeq);
	}
	


}
