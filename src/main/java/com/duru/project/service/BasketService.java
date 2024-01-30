package com.duru.project.service;

import java.util.List;

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
	public void insertBasket(Basket basket) { 
		basketRepository.save(basket); // set해줄 필요없음. basket에 필요한 정보들은 Controller에서 다 set해줄 것. 여기선 그냥 저장만!!

		
	}
	
	@Transactional(readOnly = true) 
	public List<Basket> getBasketList(int userSeq) {
		return basketRepository.findByUser_UserSeq(userSeq);
		
	}
	
	@Transactional 
	public void updateBasket(int productSeq, int basketProductAmount) {
		Basket basket = basketRepository.findByProduct_ProductSeq(productSeq);
		basket.setBasketProductAmount(basketProductAmount);
		basket.setBasketProductPrice(basketProductAmount * basket.getProduct().getProductPrice());
		basketRepository.save(basket);
		}
	
	
	@Transactional
	public void deleteBasket(int basketSeq) {
		basketRepository.deleteById(basketSeq);
	}
	
	@Transactional
	public void deleteUserBasket(int userSeq) {
		basketRepository.deleteByUser_UserSeq(userSeq);
	}

}
