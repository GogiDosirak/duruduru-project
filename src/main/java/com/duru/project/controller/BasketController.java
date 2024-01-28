package com.duru.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Basket;
import com.duru.project.domain.Product;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.BasketService;
import com.duru.project.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BasketController {
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/basket")
	public String basket(HttpSession session) {
		User user = (User) session.getAttribute("principal");
		int userSeq = user.getUserSeq();
		int totalPrice = 0;
		List<Basket> basketList = basketService.getBasketList(userSeq);
		for (Basket basket : basketList) {
			totalPrice += basket.getBasketProductPrice();
		} // 장바구니 총 금액 처리 
		session.setAttribute("totalPrice", totalPrice); 
		session.setAttribute("basketList", basketList);
		return "shop/basket/basket";
	}
	
	@PostMapping("/insertBasket")
	public String insertBasket(int userSeq,int productSeq, int productAmount, HttpSession session) {
		List<Basket> basketList = basketService.getBasketList(userSeq);
		boolean presentProduct = false; 
		Basket presentBasket =  null;
		for (Basket basket : basketList) {
			if(basket.getProduct().getProductSeq() == productSeq) {
				presentProduct = true;
				presentBasket = basket;  //basketList를 돌면서 해당 상품이 있는지 확인, 있다면 presentProduct를 true로 변경 후 그 basket을 받음
			}
			
		}
		// 장바구니에 해당 품목이 있는경우
		if(presentProduct == true) { //그 후 수정된 값만 더해서 저장해주면, 이미 있는 상품이라면 수량과 총액만 바뀜
			presentBasket.setBasketProductAmount(presentBasket.getBasketProductAmount() + productAmount);
			presentBasket.setBasketProductPrice(presentBasket.getBasketProductAmount() * presentBasket.getProduct().getProductPrice());
			basketService.insertBasket(presentBasket);
			return "redirect:/basket"; 
			//장바구니에 해당 품목이 없는 경우
		} else { // 없는 경우엔 새로운 basket 항목을 만들어서 저장해주고 insert
		
		Basket basket = new Basket();
		User user = (User)session.getAttribute("principal"); 
		Product product = productService.getProduct(productSeq); //jsp와 js를 통해 받은 productSeq로 상품 찾아서 세팅해주기
		basket.setUser(user);
		basket.setProduct(product);
		basket.setBasketProductPrice(productAmount * basket.getProduct().getProductPrice());
		basket.setBasketProductAmount(productAmount);
	
		basketService.insertBasket(basket);
		return "redirect:/basket"; // redirect로 basket으로 보내줘야 session에 들어가서 현재값이 적용됨
		}
	}
	
	@PutMapping("/updateBasket") 
	public @ResponseBody ResponseDTO<?> updateBasket(int productSeq, int basketProductAmount) {
		basketService.updateBasket(productSeq, basketProductAmount);
		return new ResponseDTO<>(HttpStatus.OK.value(),"수정 완료");
		
	}
	
	@DeleteMapping("/deleteBasket/{basketSeq}")
	public @ResponseBody ResponseDTO<?> deleteBasket(@PathVariable int basketSeq) {
		basketService.deleteBasket(basketSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(),"삭제 완료");
	}

}