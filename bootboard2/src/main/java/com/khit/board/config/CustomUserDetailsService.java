package com.khit.board.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khit.board.entity.Member;
import com.khit.board.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db에 있는 회원 정보를 조회한다
		//UserDetails 타입의 객체를 반환한다
		Optional<Member> findMember = memberRepository.findByMemberId(username);
		if(findMember.isEmpty()) {
			throw new UsernameNotFoundException(username + " 사용자가 없습니다.");
		}else {
			//이걸 쓰면 비밀번호(암호)가 안나온다
			Member member = findMember.get();
			return new SecurityUser(member);
		}
		
	}
}
