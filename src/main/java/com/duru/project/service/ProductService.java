

package com.duru.project.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Product;
import com.duru.project.persistence.BasketRepository;
import com.duru.project.persistence.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BasketRepository basketRepository;

	@Transactional
	public void insertProduct(Product product, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로를 지정
		UUID uuid = UUID.randomUUID(); // 식별자(이름) 랜덤 생성
		String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙은 다음에 _원래파일이름(매개변수로 들어온)
		File saveFile = new File(projectPath, fileName); // 파일 껍데기를 생성해줄건데 projectPath 경로에 넣어줄거고 이름은 위의 파일네임 (매개변수 file을 넣어줄 껍데기 생성)
		file.transferTo(saveFile); // Exception 해줘야 밑줄 사라짐
		
		product.setProductFilename(fileName); //저장된 파일의 이름
		product.setProductFilepath("/files/" + fileName);  //저장된 파일의 경로와 이름 set

		productRepository.save(product);

	}

	@Transactional(readOnly = true)
	public Page<Product> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Product getProduct(int productSeq) {
		Product product = productRepository.findById(productSeq).get();
		return product;
	}
	
	@Transactional
	public void deleteProduct(int productSeq) {
		basketRepository.deleteByProduct_ProductSeq(productSeq);
		productRepository.deleteById(productSeq);
		
		
	}
	
	@Transactional
	public void updateProduct(int productSeq, Product product, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
		Product findProduct = productRepository.findById(productSeq).get(); //seq를 받아서 맞는 상품을 찾은 뒤 저장		
		if(file.getSize() != 0) { //파일 사이즈가 0이 아닌경우 (즉, 썸네일이 새로 업로드 된 경우에만)
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; 
		UUID uuid = UUID.randomUUID(); 
		String fileName = uuid + "_" + file.getOriginalFilename(); 
		File saveFile = new File(projectPath, fileName); 
		file.transferTo(saveFile);
		product.setProductFilename(fileName);
		product.setProductFilepath("/files/" + fileName);
		findProduct.setProductFilename(product.getProductFilename());
		findProduct.setProductFilepath(product.getProductFilepath());	 // 파일패스와 파일네임을 새로 지정해주고
	}
		findProduct.setProductName(product.getProductName()); //아닌 경우엔 그냥 원래 파일네임,경로 사용
		findProduct.setProductContent(product.getProductContent());
		findProduct.setProductPrice(product.getProductPrice());
		findProduct.setProductStock(product.getProductStock()); //수정된 값들 받아서 저장!
		
		productRepository.save(findProduct); //최종적으로 Repository에 저장

	}
	
	@Transactional
	public Page<Product> search(String keyword, Pageable pageable) {
		Page<Product> productSearchList = productRepository.findByProductNameContaining(keyword, pageable);
		return productSearchList;
	}

}