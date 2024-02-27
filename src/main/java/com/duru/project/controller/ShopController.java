package com.duru.project.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Product;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {


	@Autowired
	private ProductService productService;

	@GetMapping("/mall")
	public String getProductList(HttpSession session, 
			@PageableDefault(size=8, sort="productSeq", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
		Page<Product> productList = productService.getProductList(pageable);
		session.setAttribute("productList", productList);
		return "shop/mall/mall";
	}
	
	@GetMapping("/mall/search")
	public String search(String keyword, Model model,  
			@PageableDefault(size=8, sort="productSeq", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {
		Page<Product> productSearchList = productService.search(keyword, pageable);
		 
		model.addAttribute("keyword", keyword);
		model.addAttribute("productSearchList", productSearchList);
		
		return "shop/mall/searchProduct";
	}


	@GetMapping("/insertProduct")
	public String insertProduct() {
		return "shop/mall/insertProduct";
	}

	@PostMapping("/insertProduct") //filepath, filename 이런애들은 json 형식이 아니므로 @RequestBody 붙이지 말자.
	public String insertProduct(Product product, MultipartFile file) throws Exception { // 매개변수들 추가 후 오류
																										// throws
		productService.insertProduct(product, file); // service에서 매개변수 추가됐으므로 file 해주고
		return "redirect:/mall"; //mall로 바로 보내줘야 GetMapping /mall이 실행되고 세션에 저장됨

	}
	
	@GetMapping("/getProduct/{productSeq}")
	public String getProduct(@PathVariable int productSeq, Model model) {
		Product getProduct = productService.getProduct(productSeq);
		model.addAttribute("getProduct", getProduct);
		return "shop/mall/getProduct";
	}
	
	@DeleteMapping("/deleteProduct/{productSeq}")
	public @ResponseBody ResponseDTO<?> deleteProduct(@PathVariable int productSeq) {
		productService.deleteProduct(productSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(),"상품 삭제가 완료되었습니다.");
	}
	
	@GetMapping("/updateProduct/{productSeq}") 
	//일단 상품 수정을 누르면 updateProduct.jsp로 보내야하므로 겟매핑 해주고, 누른 상품에 대한 정보를 띄워야 하므로 seq를 기준으로 찾아주고 모델에 저장
	public String updateProduct(@PathVariable int productSeq, Model model) {
		Product getProduct = productService.getProduct(productSeq);
		model.addAttribute("getProduct", getProduct);
		return "shop/mall/updateProduct";
	}
	
	@PostMapping("/updateProduct/{productSeq}") //file 업로드를 하는 방식으론 form밖에 사용할 줄 몰랐다... js로 하고싶었는데 한계를 느끼고 form 사용
	public String updateProduct(@PathVariable int productSeq, Product product, MultipartFile file) throws Exception { 
		//form엔 Post,Get 방식밖에 없으므로 PutMapping을 사용 못하고 Postmapping을 사용하였다.
		productService.updateProduct(productSeq, product, file); //매개변수로 셋을 받아서 그대로 update
		return "redirect:/mall"; //그 후, mall로 바로 보내줘야 GetMapping 돼서 Session에 수정된 정보가 set됨. 그러므로 redirect 사용 
		
	}
	

}
