package com.khit.study.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BoardVO {
	//필드
	private int id;
	private String title;
	private String writer;
	private String content;
	private Date createdDate;
}
