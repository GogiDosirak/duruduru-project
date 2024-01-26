package com.duru.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Pet;
import com.duru.project.persistence.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;
	
	@Transactional
	public void insertPet(Pet pet, MultipartFile file) throws Exception { // 파일을 매개변수로 받기
			petRepository.save(pet);

	}
	
	@Transactional
	public List<Pet> myPetList(int userSeq){
		return petRepository.findByUser_userSeq(userSeq);
	}
	
	@Transactional
	public Pet getPet(int petSeq) {
		Pet findPet = petRepository.findById(petSeq).orElseGet(()->{
			return new Pet();
		});
		return findPet;
	}
	
	@Transactional
	public void deletePet(int petSeq) {
		petRepository.deleteById(petSeq);
	}


}
