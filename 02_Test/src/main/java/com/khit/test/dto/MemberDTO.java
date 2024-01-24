package com.khit.test.dto;

import com.khit.test.entity.Member;

import jakarta.persistence.Column;
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
@Data
public class MemberDTO {
	private Long id;
	private String username;
	private String password;
	private String email;
	
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.username(member.getUsername())
				.password(member.getPassword())
				.email(member.getEmail())
				.build();
		
		return memberDTO;
	}
}
