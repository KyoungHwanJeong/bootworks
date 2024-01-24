package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "tbl_board")	//테이블 이름
@Entity			//테이블이 생성되는 클래스
public class Board extends BaseEntity{

	@Id		// PK
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
	//write.html에서 name 값과 다른 이름으로 만들것.
	//MultipartFile과 String 타입이 서로 다르므로...
	@Column
	private String filename;	//객체 이름과 다르게 쓴다
	@Column
	private String filepath;
	
	//dto를 entity로 반환하는 정적 메서드
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(0)
				.build();
		return board;
	}
	
	//dto를 entity로 수정하여 반환하는 정적 메서드
	public static Board toUpdateNoFileEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}
	
	//dto를 entity로 수정하여 반환하는 정적 메서드
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}
}
