package com.khit.study.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {

	private Long id;
	@NotEmpty(message="제목 필수.")
	@Size(max = 255)
	private String boardTitle;
	@NotEmpty(message="작성자 필수.")
	@Size(max = 30)
	private String boardWriter;
	@NotEmpty(message="글내용 필수.")
	@Size(max = 2000)
	private String boardContent;
	private Integer boardHits;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	
}
