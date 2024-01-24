package com.khit.board.dto;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {
	private Integer id;
	private String memberId;
	private String password;
	private String name;
	private Role role;
	
	public static MemberDTO toSaveDTO(Member member) {
		
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.password(member.getPassword())
				.name(member.getName())
				.role(member.getRole())
				.build();
		return memberDTO;
	}
}
