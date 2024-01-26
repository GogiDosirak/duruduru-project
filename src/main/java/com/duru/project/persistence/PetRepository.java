package com.duru.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duru.project.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
	

}
