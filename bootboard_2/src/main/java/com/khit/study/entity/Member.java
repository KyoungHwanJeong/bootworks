package com.khit.study.entity;

import com.khit.study.dto.MemberDTO;

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
@Table(name = "tbl_member2")
@Data
@Entity
public class Member {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(unique = true)
	private String memberEmail;
	@Column(nullable = false)
	private String memberPassword;
	@Column(length = 30, nullable = false)
	private String memberName;
	@Column
	private int memberAge;
	
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.memberEmail(memberDTO.getMemberEmail())
				.memberPassword(memberDTO.getMemberPassword())
				.memberName(memberDTO.getMemberName())
				.memberAge(memberDTO.getMemberAge())
				.build();
		
		return member;
	}
	
}
