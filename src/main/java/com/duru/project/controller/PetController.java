package com.duru.project.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Pet;
import com.duru.project.domain.User;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.PetService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PetController {

	@Autowired
	PetService petService;
	
	@GetMapping("/petInfo/{userSeq}")
	public String petInfo(Model model, @PathVariable int userSeq){
		
		model.addAttribute("myPetList", petService.myPetList(userSeq));
		return "/pet/petInfo";
	}

	//insert11
	@GetMapping("/insertPet")
	public String insertPet() {
		return "/pet/insertPet";
	}

	//insert22
	@PostMapping("insertPet") // filepath, filename 이런애들은 json 형식아님.
	public String insertPet(Pet pet, MultipartFile file, HttpSession session) throws Exception {

		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 저장할 경로를 지정

		UUID uuid = UUID.randomUUID(); // 식별자(이름) 랜덤 생성

		String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙은 다음에 _원래파일이름(매개변수로 들어온)

		File saveFile = new File(projectPath, fileName); // 파일 껍데기를 생성해줄건데 projectPath 경로에 넣어줄거고 이름은 위의 파일네임 (매개변수 file을 넣어줄 껍데기 생성)

		file.transferTo(saveFile); // Exception 해줘야 밑줄 사라짐
		
		pet.setPetFilename(fileName); //저장된 파일의 이름
		pet.setPetFilepath("/files/" + fileName);  //저장된 파일의 경로와 이름 set

		User findUser = (User)session.getAttribute("principal");
		pet.setUser(findUser);
		petService.insertPet(pet, file); // service에서 매개변수 추가됐으므로 file 해주고
		return "/pet/petInfo";
	}
	
	//getPet
	@GetMapping("/getPet/{petSeq}")
	public String getPet(@PathVariable int petSeq, Model model) {
		Pet findPet = petService.getPet(petSeq);
		model.addAttribute("pet", findPet);
		return "/pet/petInfo";
	}
	
	//updatePet11
	@GetMapping("/updatePet/{petSeq}")
	public String updatePet(@PathVariable int petSeq, Model model) {
		model.addAttribute("pet", petService.getPet(petSeq));
		return "/pet/updatePet";
	}
	
	//updatePet22
	@PostMapping("/updatePet/{petSeq}")
	public String updatePet(@PathVariable int petSeq, Pet pet, MultipartFile file) throws Exception{
		Pet findPet = petService.getPet(petSeq);
		findPet.setPetName(pet.getPetName());
		findPet.setPetBirthday(pet.getPetBirthday());
		findPet.setPetType(pet.getPetType());
		if(file.getSize()!=0) {
			String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; 
			UUID uuid = UUID.randomUUID(); 
			String fileName = uuid + "_" + file.getOriginalFilename(); 
			File saveFile = new File(projectPath, fileName); 
			file.transferTo(saveFile);
			findPet.setPetFilename(fileName);
			findPet.setPetFilepath("/files/" + fileName);
		}
		petService.insertPet(findPet, file);
		return "redirect:/petInfo/"+findPet.getUser().getUserSeq();
	}
	
	//deletePet
	@DeleteMapping("/deletePet/{petSeq}")
	public @ResponseBody ResponseDTO<?> deletePet(@PathVariable int petSeq){
		petService.deletePet(petSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(),"동물정보 삭제가 완료되었습니다.");
	}

}
