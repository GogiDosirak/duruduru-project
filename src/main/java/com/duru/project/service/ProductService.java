package com.duru.project.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Product;
import com.duru.project.persistence.ProductRepository;

@Service
public class ProductService {

   @Autowired
   private ProductRepository productRepository;

   @Transactional
   public void insertProduct(Product product, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
      String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로를 지정

      UUID uuid = UUID.randomUUID(); // 식별자(이름) 랜덤 생성

      String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙은 다음에 _원래파일이름(매개변수로 들어온)

      File saveFile = new File(projectPath, fileName); // 파일 껍데기를 생성해줄건데 projectPath 경로에 넣어줄거고 이름은 위의 파일네임 (매개변수 file을 넣어줄 껍데기 생성)

      file.transferTo(saveFile); // Exception 해줘야 밑줄 사라짐
      
      product.setFilename(fileName); //저장된 파일의 이름
      product.setFilepath("/files/" + fileName);  //저장된 파일의 경로와 이름 set

      productRepository.save(product);

   }

   @Transactional(readOnly = true)
   public List<Product> getProductList() {
      return productRepository.findAll();
   }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
   
   @Transactional(readOnly = true)
   public Product getProduct(int product_seq) {
      Product product = productRepository.findById(product_seq).get();
      return product;
   }
   
   @Transactional
   public void deleteProduct(int product_seq) {
      productRepository.deleteById(product_seq);
   }

}