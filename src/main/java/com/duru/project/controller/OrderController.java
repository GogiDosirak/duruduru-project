package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Basket;
import com.duru.project.domain.Product;
import com.duru.project.domain.User;
import com.duru.project.domain.UserOrder;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.BasketService;
import com.duru.project.service.OrderService;
import com.duru.project.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BasketService basketService;
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/order/{userSeq}")
	public String order(@PathVariable int userSeq, HttpSession session) { //jsp에서 "order/${principal.userSeq}"로 받아옴
		session.getAttribute("basketList"); //session에서 장바구니 물품 불러와야 물품이 뜸
		return "shop/order/order";
	}
	
	
	@PostMapping("/insertOrder")
	public @ResponseBody ResponseDTO<?> insertOrder(@RequestBody UserOrder userOrder, HttpSession session) {
		User user = (User)session.getAttribute("principal");
		userOrder.setUser(user);
		orderService.insertOrder(userOrder);
		session.setAttribute("userOrder", userOrder);
		return new ResponseDTO<>(HttpStatus.OK.value(), "결제하기 창으로 넘어갑니다.");
		
	}
	
	
	@GetMapping("/goPay")
	public String pay() {
		return "/shop/order/goPay";
	}
	
	@DeleteMapping("/deleteOrder/{orderSeq}")
	public @ResponseBody ResponseDTO<?> deleteOrder(@PathVariable int orderSeq) {
		orderService.deleteOrder(orderSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "처음으로 넘어갑니다.");
	}
	
	@GetMapping("/buyComplete")
	public String buyComplete(HttpSession session) {
		List<Basket> userBasketList = (List<Basket>)session.getAttribute("basketList");
		for (Basket basket : userBasketList) {
			// 유저 basket에 담긴 모든 상품에 대해서
			Product product = basket.getProduct(); // 해당 상품의 stock에서 basket에 담긴 product를 빼내고
			productService.updateProductStock(product, basket.getBasketProductAmount()); //updateProductStock을 통해 amount만큼 빼준 값을 set해줌
			//
			
		}
		// 그 후, 
		User user = (User)session.getAttribute("principal");;
		basketService.deleteUserBasket(user.getUserSeq()); //해당 유저의 basket을 삭제
	
		
		return "/index";
		
		
	}
	
	@GetMapping("/orderHistory")
	public String orderHistory(HttpSession session) {
		User user = (User) session.getAttribute("principal");
		List<UserOrder> orderList = orderService.getOrderList(user.getUserSeq());
		session.setAttribute("orderList", orderList);
		return "shop/order/orderHistory";
	}
	

}
