package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

// @Repository 생략 가능
//JPAReporsitory를 상속 받아야 함
public interface MemberRepository extends JpaRepository<Member, Long>{

	//제공된 메서드(CRUD)-save(), findAll(), findById(), deleteById()
	//쿼리 메서드(메서드 이름이 쿼리를 나타낸다) - Optional(null 체크하는 클래스)
	// select * from tbl_member where member_email = ?;
	Optional<Member> findByMemberEmail(String memberEmail);
}
