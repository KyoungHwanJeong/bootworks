package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	private final PasswordEncoder pwEncoder;

	public Member login(Member member) {
		//db에서 아이디 조회
		Optional<Member> findMember =
				memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}
	
	public void save(MemberDTO memberDTO) {
		//1. 비밀번호 암호화
		//2. 권한 설정
		String encPw = pwEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encPw);
		memberDTO.setRole(Role.MEMBER);
		Member member = Member.toSaveEntity(memberDTO);
		
		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		List<Member> memberList = memberRepository.findAll();
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for(Member member : memberList) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		
		return memberDTOList;
	}

	public MemberDTO findById(Integer id) {
		Optional<Member> findMember = memberRepository.findById(id);
		if(findMember.isPresent()) {	//회원 정보가 있으면
			//정보를 가져와서 반환한다
			MemberDTO memberDTO = MemberDTO.toSaveDTO(findMember.get());
			return memberDTO;
		}else {
			throw new BootBoardException("페이지를 찾을 수 없습니다.");
		}
	}

	public void deleteById(Integer id) {
		memberRepository.deleteById(id);
	}

	public void update(MemberDTO memberDTO) {
		Member member = Member.toUpdateEntity(memberDTO);
		
		memberRepository.save(member);
	}

	
}
