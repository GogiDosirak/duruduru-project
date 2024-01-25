package com.duru.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.Basket;
import com.duru.project.domain.Product;
import com.duru.project.domain.User;
import com.duru.project.persistence.BasketRepository;
import com.duru.project.persistence.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BasketService {
	@Autowired
	private BasketRepository basketRepository;
	
	
	@Transactional
	public void insertBasket(Basket basket) { //basket에 대한 정보는 다 product와 user로부터 받아와서 총액 구하고 총 구매개수 구하는거라 여기서 불러서 set해줄 것
		basketRepository.save(basket);

		
	}

}
