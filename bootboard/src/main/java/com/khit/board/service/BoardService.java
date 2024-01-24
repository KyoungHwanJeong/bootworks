package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardrepository;

	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws Exception, IOException {
		//1. 파일을 서버에 저장하고, 
		if(!boardFile.isEmpty()) {	//전달된 파일이 있으면
			//저장 경로
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			
			UUID uuid = UUID.randomUUID();	//무작위 아이디가 생성된다(중복 파일이 생기지 않게 생성해준다)
			String filename = uuid + "_" +boardFile.getOriginalFilename();	//원본 파일
			
			//File 클래스 객체 생성
			File savedFile = new File(filepath, filename);	//upload 폴더에 저장될 파일
			boardFile.transferTo(savedFile);
			
		//2. 파일 이름은 db에 저장
			boardDTO.setFilename(filename);//원본 파일
			boardDTO.setFilepath("/upload/" + filename);//저장 경로
		}
		//dto -> entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		//entity를 db에 저장
		boardrepository.save(board);
	}

	public List<BoardDTO> findAll() {
		//db에서 entity list를 가져온다
		//List<Board> boardList = boardrepository.findAll();
		//내림차순 정렬 - Sort.by(정렬방식, 해당필드)
		List<Board> boardList = 
				boardrepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//게시글을 담을 빈 리스트 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board: boardList) {
			//entity -> dto 변환
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		//Optional - 선택(에러 처리)
		Optional<Board> findBoard = boardrepository.findById(id);
		if(findBoard.isPresent()) {	//검색한 게시글이 있으면 컨트롤러로 반환한다
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
			return boardDTO;
		}else {		//게시글이 없으면 에러 처리한다
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}

	@Transactional	//컨트롤러 작업(매세드)이 2개 이상이면 사용한다
	public void updateHits(Long id) {
		//이 메서드를 boardRepository에 생성
		boardrepository.updateHits(id);
	}

	public void deleteById(Long id) {
		boardrepository.deleteById(id);
	}

	public void update(BoardDTO boardDTO, MultipartFile boardFile) throws IllegalStateException, IOException {
		
		Board board = null;
		UUID uuid = UUID.randomUUID();	//무작위 아이디가 생성된다(중복 파일이 생기지 않게 생성해준다)
		String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
		String filename = uuid + "_" +boardFile.getOriginalFilename();	//원본 파일
		File saveFile = new File(filepath, filename);	//파일 존재를 확인하기 위한 경로
		
		/*
		//1. 파일을 서버에 저장하고, 
			
		if(!boardFile.isEmpty()) {	//전달된 파일이 있으면
			//저장 경로
			filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			
			filename = uuid + "_" +boardFile.getOriginalFilename();	//원본 파일
			
			//File 클래스 객체 생성
			File savedFile = new File(filepath, filename);	//upload 폴더에 저장될 파일
			boardFile.transferTo(savedFile);
			
		//2. 파일 이름은 db에 저장
			boardDTO.setFilename(filename);//원본 파일
			boardDTO.setFilepath("/upload/" + filename);//저장 경로
		}else {
			//save() - 삽입(id가 없는 경우), 수정(id가 있는 경우)
			//dto -> entity
			//첨부파일이 없는 경우
			boardDTO.setFilepath(findById(boardDTO.getId()).getFilepath());
		}
		
		//save() - 삽입(id가 없는 경우), 수정(id가 있는 경우)
		//dto -> entity
		board = Board.toUpdateEntity(boardDTO);
		boardrepository.save(board);
		*/
		
		if(!boardFile.isEmpty()) {
			board = Board.toUpdateEntity(boardDTO);
			boardrepository.save(board);
			
			String fileName = uuid + "_" +boardFile.getOriginalFilename();	//원본 파일
			boardFile.transferTo(saveFile);
			
			boardDTO.setFilename(fileName);
			boardDTO.setFilepath("/upload/" + filename);
		}else {
			board = Board.toUpdateNoFileEntity(boardDTO);
			boardrepository.save(board);
		}
		}

	public Page<BoardDTO> findListAll(Pageable pageable) {
		
		int page = pageable.getPageNumber() - 1;	//db는 현재 페이지보다 1 작으므로
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardrepository.findAll(pageable);
		
		log.info("boardList.isFirst()=" + boardList.isFirst());
		log.info("boardList.isLast()=" + boardList.isLast());
		log.info("boardList.isLast()=" + boardList.getNumber());
		
		//생성자 방식으로 boardDTOList를 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
				board.getId(),board.getBoardTitle(),board.getBoardWriter(),
				board.getBoardContent(),board.getBoardHits(),
				board.getFilename(),board.getFilepath(),
				board.getCreatedDate(),board.getUpdatedDate()));
		
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {
		
		int page = pageable.getPageNumber() - 1;	//db는 현재 페이지보다 1 작으므로
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardrepository.findByBoardTitleContaining(keyword, pageable);
		
		log.info("boardList.isFirst()=" + boardList.isFirst());
		log.info("boardList.isLast()=" + boardList.isLast());
		log.info("boardList.isLast()=" + boardList.getNumber());
		
		//생성자 방식으로 boardDTOList를 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
				board.getId(),board.getBoardTitle(),board.getBoardWriter(),
				board.getBoardContent(),board.getBoardHits(),
				board.getFilename(),board.getFilepath(),
				board.getCreatedDate(),board.getUpdatedDate()));
		
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1;	//db는 현재 페이지보다 1 작으므로
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardrepository.findByBoardContentContaining(keyword, pageable);
		
		log.info("boardList.isFirst()=" + boardList.isFirst());
		log.info("boardList.isLast()=" + boardList.isLast());
		log.info("boardList.isLast()=" + boardList.getNumber());
		
		//생성자 방식으로 boardDTOList를 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
				board.getId(),board.getBoardTitle(),board.getBoardWriter(),
				board.getBoardContent(),board.getBoardHits(),
				board.getFilename(),board.getFilepath(),
				board.getCreatedDate(),board.getUpdatedDate()));
		
		return boardDTOList;
	}

}
