package com.duru.project.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duru.project.domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	List<Pet> findByUser_userSeq(int userSeq);
	

}
