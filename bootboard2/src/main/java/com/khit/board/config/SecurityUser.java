package com.khit.board.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.khit.board.entity.Member;

public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	
	// user 대신에 member로 커스터마이징함
	private Member member;
	//3가지 파라미터 - username, password, role
	public SecurityUser(Member member) {
		//암호화 안된 상태는 {noop} + member.getPassword()를 사용한다 => 암호화 되면 지운다
		super(member.getMemberId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
}
