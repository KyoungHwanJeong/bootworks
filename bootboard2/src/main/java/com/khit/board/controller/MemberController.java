package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	
	//로그인 페이지 요청: /member/login
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";	//login.html
	}
	
	/*
	//로그인 처리
	@PostMapping("/member/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		if(loginMember != null
				&& loginMember.getPassword().equals(member.getPassword())) {
			//아이디 비밀번호 일치해서 로그인 되면 세션 발급
			session.setAttribute("sessMemberId", loginMember.getMemberId());
			return "main";
		}else {
			return "/member/login";
		}
	}
	*/
	
	//메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";	//main.html
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		return "redirect:/";
	}
	
	//회원 가입 페이지
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	
	//회원 가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.save(memberDTO);
		return "redirect:/member/login";
	}
	
	//회원목록
	@GetMapping("/member/list")
	public String list(Model model) {
		List<MemberDTO> memberList = memberService.findAll();
		model.addAttribute("memberList", memberList);
		return "/member/list";
	}
	
	//상세보기
	@GetMapping("/member/{id}")
	public String detail(@PathVariable Integer id,
			Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		
		return "/member/detail";
	}
	
	//회원 삭제하기
	@GetMapping("/member/delete/{id}")
	public String delete(@PathVariable Integer id) {
		memberService.deleteById(id);
		
		return "redirect:/member/list";
	}
	
	/*
	//수정 페이지 가져오기
	@GetMapping("/member/update/{id}")
	public String updateForm(@AuthenticationPrincipal MemberDTO memberDTO
			, Model model) {
		if(memberDTO.getId() != null) {
			memberDTO = (String)memberDTO.getId();
			model.addAttribute("member", memberDTO);
		}
		
		return "/member/update";
	}
	*/
	/*
	//수정하기
	@PostMapping("/member/update/{id}")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		
		return "redirect:/member/" + memberDTO.getId();
	}
	*/
	/*
	@GetMapping("/admin")
	public String adminRole() {
		SecurityContextHolder.getContext().getAuthentication()
			.getAuthorities().stream()
			.filter(a -> a.getAuthority().equals("ADMIN"))
			.findFirst()
			.orElseThrow(() -> new AccessDeniedException("Access denied"));
	}
	*/
}
