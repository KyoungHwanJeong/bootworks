package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest2 {
	
	private BoardRepository boardRepository;
	
	//테스트 데이터 생성(200개)
	@Test
	@BeforeEach
	public void dataCreate() {
		for(int i=1; i<=200; i++) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setContent("테스트 내용: " + i);
			board.setWriter("테스터");
			board.setCreatedDate(now);
			
			boardRepository.save(board);
		}
	}
	
	/*
	@Test
	public void testFindByTitle() {
		//findByTitle() 호출하기
		List<Board> boardList =
			boardRepository.findByTitle("테스트 제목 10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}
	*/
	
	/*
	@Test
	public void testFindByTitleContaining() {
		//findByTitle() 호출
		List<Board> boardList = boardRepository.findByTitleContaining("테스트 제목 10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/

	/*
	@Test 
	public void testFindByTitleContainingOrContentContaining() {
	  List<Board> boardList =
			  boardRepository.findByTitleContainingOrContentContaining("10", "17");
	  
	  boardList.forEach(board -> log.info(board.toString())); 
	}
	*/
	/*
	@Test
	public void testFindByTitleContainingOrderByIdDesc() {
		List<Board> boardList =
				boardRepository.findByTitleContainingOrderByIdDesc("10");
		for(Board board: boardList) {
			log.info(board.toString());
		}
		
	}
	*/
	
	@Test
	public void testFindByTitleContaining() {
		//0 => 1페이지
		//Pageable paging = PageRequest.of(0, 10); //오름차순 정렬
		Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));	//내림차순 정렬
		log.info("page: " + paging.getPageNumber());	//페이지 번호
		log.info("size: " + paging.getPageSize());		//페이지 당 글 개수
		/*
		List<Board> boardList =
				boardRepository.findByTitleContaining("제목", paging);
		*/
		//boardList.forEach(board -> log.info(board.toString()));
	}
	
}
