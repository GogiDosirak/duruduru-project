package com.duru.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.duru.project.domain.Pet;
import com.duru.project.domain.RoleType;
import com.duru.project.domain.User;
import com.duru.project.domain.WalkCheckBoard;
import com.duru.project.dto.ResponseDTO;
import com.duru.project.service.PetService;
import com.duru.project.service.WalkCheckBoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WalkCheckBoardController {

	@Autowired
	private WalkCheckBoardService walkCheckBoardService;

	@Autowired
	private PetService petService;

	@PostMapping("/insertwachbo") // filepath, filename 이런애들은 json 형식이 아니므로 @RequestBody 붙이지 말자.
	public String insertProduct(WalkCheckBoard walkCheckBoard, MultipartFile file, HttpSession session)
			throws Exception { // 매개변수들 추가 후 오류
		User finduser = (User) session.getAttribute("principal");
		walkCheckBoard.setUser(finduser); // throws
		walkCheckBoardService.insertwach(walkCheckBoard, file); // service에서 매개변수 추가됐으므로 file 해주고
		return "redirect:/walkcheckboard"; // mall로 바로 보내줘야 GetMapping /mall이 실행되고 세션에 저장됨

	}

	@GetMapping("/walkcheckboard")
	public String getProductList(HttpSession session) {
		User findUser = (User) session.getAttribute("principal");

		if (findUser == null) {
			return "redirect:/login";
		}
		List<Pet> findPetList = petService.myPetList(findUser.getUserSeq());
		if (findPetList.size() == 0 && findUser.getRole() != RoleType.ADMIN) {
		    return "redirect:/insertPet";
		}
		List<WalkCheckBoard> wachList = walkCheckBoardService.getwachList();
		session.setAttribute("getwachList", wachList);
		return "board/walking/walkcheckboard";
	}

	@GetMapping("/getWach/{wachboSeq}")
	public String getWach(Model model, @PathVariable int wachboSeq,HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		WalkCheckBoard walkCheckBoard = walkCheckBoardService.getwach(wachboSeq);
		model.addAttribute(walkCheckBoard);
		return "board/walking/getwalkcheckboard";
	}

	@GetMapping("/updateWach/{wachboSeq}")
	public String updateProduct(@PathVariable int wachboSeq, Model model,HttpSession session) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리 수행
            return "redirect:/login";
        }
		WalkCheckBoard walkCheckBoard = walkCheckBoardService.getwach(wachboSeq);
		model.addAttribute(walkCheckBoard);
		return "board/walking/updatewalkcheckboard";
	}

	@PostMapping("/updateWach/{wachboSeq}") // file 업로드를 하는 방식으론 form밖에 사용할 줄 몰랐다... js로 하고싶었는데 한계를 느끼고 form 사용
	public String updateProduct(@PathVariable int wachboSeq, WalkCheckBoard walkCheckBoard, MultipartFile file)
			throws Exception { // form엔 Post,Get 방식밖에 없으므로 PutMapping을 사용 못하고 Postmapping을 사용하였다.
		walkCheckBoardService.updatewach(wachboSeq, walkCheckBoard, file); // 매개변수로 셋을 받아서 그대로 update
		return "redirect:/walkcheckboard"; // 그 후, mall로 바로 보내줘야 GetMapping 돼서 Session에 수정된 정보가 set됨. 그러므로 redirect 사용

	}

	@DeleteMapping("/deleteWach/{wachboSeq}")
	public @ResponseBody ResponseDTO<?> deleteProduct(@PathVariable int wachboSeq) {
		walkCheckBoardService.deleteWach(wachboSeq);
		return new ResponseDTO<>(HttpStatus.OK.value(), "글 삭제가 완료되었습니다.");
	}

	@PutMapping("/consumepoint/{wachboSeq}")
	public @ResponseBody ResponseDTO<?> consumepoint(@PathVariable int wachboSeq) {
		WalkCheckBoard walkCheckBoard = walkCheckBoardService.getwach(wachboSeq);
		int pointToConsume = 1;
		walkCheckBoard.setWachboCheck(walkCheckBoard.getWachboCheck() - pointToConsume);
		walkCheckBoardService.consumepoint(walkCheckBoard);
		return new ResponseDTO<>(HttpStatus.OK.value(), "산책 인증 완료");
	}

}
