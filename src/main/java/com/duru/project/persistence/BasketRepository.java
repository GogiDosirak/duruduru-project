package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

}
