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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.Basket;
import com.duru.project.domain.Product;
import com.duru.project.domain.User;
import com.duru.project.domain.UserOrder;
import com.duru.project.domain.UserPay;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.BasketService;
import com.duru.project.service.OrderService;
import com.duru.project.service.ProductService;
import com.duru.project.service.UserPayService;
import com.duru.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BasketService basketService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserPayService userPayService;
	
	
	@GetMapping("/order/{userSeq}")
	public String order(@PathVariable int userSeq, HttpSession session) { //jsp에서 "order/${principal.userSeq}"로 받아옴
		
		User user = (User) session.getAttribute("principal");

		if (user == null) {
			// 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
			return "redirect:/login";
		}
		
		
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
	public String pay(Model model) {
		return "/shop/order/goPay";
	}
	
	@DeleteMapping("/deleteOrder/{orderSeq}")
	public @ResponseBody ResponseDTO<?> deleteOrder(@PathVariable int orderSeq) {
		orderService.deleteOrder(orderSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "처음으로 넘어갑니다.");
	}
	
	@GetMapping("/buyComplete")
	public String buyComplete(HttpSession session) {
		// 결제가 완료되면 userpay 테이블에 데이터 삽입
		UserOrder userOrder = (UserOrder)session.getAttribute("userOrder");
		User user = (User)session.getAttribute("principal");
		UserPay userPay = new UserPay();
		userPay.setUser(user);
		userPay.setPayAddress(userOrder.getOrderAddress());
		userPay.setPayAddressDetail(userOrder.getOrderAddressDetail());
		userPay.setPayEmail(userOrder.getOrderEmail());
		userPay.setPayName(userOrder.getOrderName());
		userPay.setPayPhonenumber(userOrder.getOrderPhonenumber());
		userPay.setPayPrice(userOrder.getOrderPrice());
		userPay.setPayRequest(userOrder.getOrderRequest());
		userPay.setPayZipcode(userOrder.getOrderZipcode());
		//userOrder에서 값을 받아서 set해주고,
		userPayService.insertUserPay(userPay); //저장
		
		//결제 완료되면 해당 userOrder 삭제
		orderService.deleteOrder(userOrder.getOrderSeq());
		
		List<Basket> userBasketList = (List<Basket>)session.getAttribute("basketList");
		for (Basket basket : userBasketList) {
			// 유저 basket에 담긴 모든 상품에 대해서
			Product product = basket.getProduct(); // 해당 상품의 stock에서 basket에 담긴 product를 빼내고
			productService.updateProductStock(product, basket.getBasketProductAmount()); //updateProductStock을 통해 amount만큼 빼준 값을 set해줌
			//
			
		}
		// 그 후, 
		if(session.getAttribute("point") != null) { //point를 사용한 경우에만 해당 내용이 실행돼야함
		int point = (int) session.getAttribute("point");
		user.setPoint(user.getPoint()-point); // 사용한 만큼 포인트 깎아줌
		userService.insertUser(user); // 저장
		session.removeAttribute("point"); //저장해뒀던 포인트 정보 remove
		}
		basketService.deleteUserBasket(user.getUserSeq()); //해당 유저의 basket을 삭제
	
		return "/index";
		
		
	}
	
	
	@GetMapping("/usePoint/{userSeq}")
	public String usePoint(@PathVariable int userSeq,int point, HttpSession session) {
		session.setAttribute("point", point);
		return "shop/order/usePoint";
	}

}
