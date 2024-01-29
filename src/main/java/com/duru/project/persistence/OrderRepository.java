package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Integer>{
	

}
  