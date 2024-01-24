package com.khit.study.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Board {
	//필드
	//ORM 방식
	@Id	// 기본키(설정안하면 오류남)
	@GeneratedValue(strategy=GenerationType.IDENTITY) //자동순번 auto_increment
	private int id;
	
	@Column(length=400, nullable=false)	//길이 400 byte not null
	private String title;
	@Column(length=30, nullable=false) //길이 30 byte not null
	private String writer;
	@Column(length=4000, nullable=false) //길이 4000 byte not null
	private String content;
	@CreationTimestamp	//현재 날짜와 시간을 자동 생성한다
	private Timestamp createdDate;
}
