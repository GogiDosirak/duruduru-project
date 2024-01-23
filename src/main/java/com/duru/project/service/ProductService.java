package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duru.project.domain.Product;
import com.duru.project.persistence.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public void insertProduct(Product product) {
    productRepository.save(product);
		
	}
	
	@Transactional(readOnly = true)
	public List<Product> getProductList() {
	 return	productRepository.findAll();
	}

}
