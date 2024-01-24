package com.khit.study.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.study.dto.MemberDTO;
import com.khit.study.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	
	//회원가입 폼
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	
	//회원가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.save(memberDTO);
		
		return "redirect:/member/join";
	}
	
	//회원 목록 출력
	@GetMapping("/member/list")
	public String getList(Model model) {
		List<MemberDTO> memberList = memberService.findAll();
		model.addAttribute("memberList", memberList);
		
		return "/member/list";
	}
	
	//회원 상세보기
	@GetMapping("/member/{id}")
	public String getMember(@PathVariable Long id,
			Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail";
	}
	
	//회원 삭제
	@GetMapping("/member/delete/{id}")
	public String delete(@PathVariable Long id,
			HttpSession session) {
		memberService.deleteById(id);
		session.invalidate();
		
		return "redirect:/member/list";
	}
	
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
}
