package com.duru.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duru.project.domain.ImageSrcExtractor;
import com.duru.project.domain.Product;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {
	public ImageSrcExtractor imageSrcExtractor;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/mall")
	public String getProductList(HttpSession session) {
	    List<Product> productList = productService.getProductList();
	    session.setAttribute("productList", productList);
	
	    return "shop/mall/mall";
	}
	
	
	@GetMapping("/basket")
	public String basket() {
		return "shop/basket/basket";
	}
	
	@GetMapping("/insertProduct")
	public String insertProduct() {
		return "shop/mall/insertProduct";
	}
	
	@PostMapping("/insertProduct")
	public @ResponseBody ResponseDTO<?> insertProduct(@RequestBody Product product) {
		productService.insertProduct(product);
		return new ResponseDTO<>(HttpStatus.OK.value(), "상품 등록이 완료되었습니다.");
	}
	

	
	

}





