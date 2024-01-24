package com.khit.board.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice	//예외처리 역할을 담당하는 클래스
@RestController		//문자를 반환하는 클래스
public class BootBoardExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String globalExceptionHandler(Exception e) {
		
		return "<h2>" + e.getMessage() + "</h2>";	//예외 처리를 할 때 문자로 보낸 데이터를 반환한다
	}
}
