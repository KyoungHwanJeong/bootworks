package com.khit.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByMemberEmail(String memberEmail);
}
