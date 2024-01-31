package com.duru.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;

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

   @PostMapping("/insertProduct") //filepath, filename 이런애들은 json 형식이 아니므로 @RequestBody 붙이지 말자.
   public String insertProduct(Product product, MultipartFile file) throws Exception { // 매개변수들 추가 후 오류
                                                                              // throws
      productService.insertProduct(product, file); // service에서 매개변수 추가됐으므로 file 해주고
      return "redirect:/mall"; //mall로 바로 보내줘야 GetMapping /mall이 실행되고 세션에 저장됨

   }
   
   @GetMapping("/getProduct/1")
   public String getProduct(Model model) {
      Product getProduct = productService.getProduct(1);
      model.addAttribute("getProduct", getProduct);
      return "shop/mall/practice";
   }
   
   @DeleteMapping("/deleteProduct/{product_seq}")
   public @ResponseBody ResponseDTO<?> deleteProduct(@PathVariable int product_seq) {
      productService.deleteProduct(product_seq);
      return new ResponseDTO<>(HttpStatus.OK.value(),"상품 삭제가 완료되었습니다.");
   }
   
   @GetMapping("/updateProduct/{product_seq}")
   public String updateProdutc(@PathVariable int product_seq) {
      return "shop/mall/updateProduct";
   }
}
