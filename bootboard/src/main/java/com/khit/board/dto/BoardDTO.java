package com.khit.board.dto;

import java.time.LocalDateTime;

import com.khit.board.entity.Board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class BoardDTO {
	private Long id;
	
	@NotEmpty(message="제목은 필수 항목입니다.")
	@Size(max = 255)
	private String boardTitle;
	@NotEmpty(message="작성자는 필수 항목입니다.")
	@Size(max = 30)
	private String boardWriter;
	@NotEmpty(message="글내용은 필수 항목입니다.")
	@Size(max = 2000)
	private String boardContent;
	private Integer boardHits;
	private String filename;
	private String filepath;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//entity -> dto로 변환할 정적 메서드
	public static BoardDTO toSaveDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(board.getId())
				.boardTitle(board.getBoardTitle())
				.boardWriter(board.getBoardWriter())
				.boardContent(board.getBoardContent())
				.boardHits(board.getBoardHits())
				.filename(board.getFilename())
				.filepath(board.getFilepath())
				.createdDate(board.getCreatedDate())
				.updatedDate(board.getUpdatedDate())
				.build();
		return boardDTO;
	}
	
}
