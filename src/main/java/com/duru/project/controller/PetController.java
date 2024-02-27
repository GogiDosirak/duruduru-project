package com.duru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String petInfo(Model model, @PathVariable int userSeq, HttpSession session){
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		model.addAttribute("myPetList", petService.myPetList(userSeq));
		return "/pet/petInfo";
	}

	//insert11
	@GetMapping("/insertPet")
	public String insertPet(HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		return "/pet/insertPet";
	}

	//insert22
	@PostMapping("insertPet") // filepath, filename 이런애들은 json 형식아님.
	public String insertPet(Pet pet, MultipartFile file, HttpSession session) throws Exception {

		User findUser = (User)session.getAttribute("principal");
		pet.setUser(findUser);
		petService.insertPet(pet, file); // service에서 매개변수 추가됐으므로 file 해주고
		return "redirect:/petInfo/"+findUser.getUserSeq();
	}
	
	//getPet
	@GetMapping("/getPet/{petSeq}")
	public String getPet(@PathVariable int petSeq, Model model, HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		Pet findPet = petService.getPet(petSeq);
		model.addAttribute("pet", findPet);
		return "/pet/petInfo";
	}
	
	//updatePet11
	@GetMapping("/updatePet/{petSeq}")
	public String updatePet(@PathVariable int petSeq, Model model, HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		model.addAttribute("pet", petService.getPet(petSeq));
		return "/pet/updatePet";
	}
	
	//updatePet22
	@PostMapping("/updatePet/{petSeq}")
	public String updatePet(@RequestParam int userSeq, Pet pet, MultipartFile file) throws Exception{
		petService.updatePet(pet, file);
		return "redirect:/petInfo/"+userSeq;
	}
	
	//deletePet
	@DeleteMapping("/deletePet/{petSeq}")
	public @ResponseBody ResponseDTO<?> deletePet(@PathVariable int petSeq){
		petService.deletePet(petSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(),"동물정보 삭제가 완료되었습니다.");
	}

}
