package com.khit.study.service;

import org.springframework.stereotype.Service;

import com.khit.study.dto.BoardDTO;
import com.khit.study.entity.Board;
import com.khit.study.repository.BoardRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public void save(@Valid BoardDTO boardDTO) {
		Board board = Board.toSaveEntity(boardDTO);
		
		boardRepository.save(board);
	}
	
}
