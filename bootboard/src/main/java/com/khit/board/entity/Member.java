package com.khit.board.entity;

import com.khit.board.dto.MemberDTO;

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
@Builder	// @Builder를 쓰려면 @AllArgsConstructor, @NoArgsConstructor를 넣어준다
@Table(name = "tbl_member")
@Data
@Entity
public class Member {
	//ORM 방식
	@Id			//PK 기본키
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)	//유일성을 가짐, 중복 검사
	private String memberEmail;
	@Column(nullable = false)	//필수 입력, not null
	private String memberPassword;
	@Column(length = 30, nullable = false)	//길이 30byte
	private String memberName;
	@Column
	private int memberAge;
	
	//dto를 매개로 받아서 entity에 저장하는 메서드 생성
	public static Member toSaveEntity(MemberDTO memberDTO) {
		/*
		Member member = new Member();
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		*/
		//builder()로 생성
		Member member = Member.builder()
				.memberEmail(memberDTO.getMemberEmail())
				.memberPassword(memberDTO.getMemberPassword())
				.memberName(memberDTO.getMemberName())
				.memberAge(memberDTO.getMemberAge())
				.build();
		
		return member;
	}
	
	//dto를 매개로 받아서 entity에 저장하는 메서드 생성
	public static Member toUpdateEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
	}
}
