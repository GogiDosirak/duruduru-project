package com.duru.project.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Page<Product> findByProductNameContaining(String keyword, Pageable pageable);
	

}
