package com.khit.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.study.dto.BoardDTO;
import com.khit.study.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	//글쓰기 페이지 요청
	@GetMapping("/write")
	public String writeForm(@ModelAttribute BoardDTO boardDTO) {
		return "/board/write";
	}
	
	//글쓰기 처리
	@PostMapping("/write")
	public String write(@Valid BoardDTO boardDTO) {
		boardService.save(boardDTO);
		
		return "redirect:/board/list";
	}
}
