package com.khit.test.entity;

import com.khit.test.dto.MemberDTO;

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
@Table(name = "table_member")
@Data
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column
	private String email;
	
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.id(memberDTO.getId())
				.username(memberDTO.getUsername())
				.password(memberDTO.getPassword())
				.email(memberDTO.getEmail())
				.build();
		
		return member;
	}

}