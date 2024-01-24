package com.khit.study.entity;

import com.khit.study.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_board2")
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String boardTitle;
	@Column(length = 30, nullable = false)
	private String boardWriter;
	@Column(length = 2000, nullable = false)
	private String boardContent;
	@Column
	private Integer boardHits;
	
	public static Board toSaveEntity(@Valid BoardDTO boardDTO) {
		Board board = Board.builder()
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(0)
				.build();
		
		return board;
	}
	
}
