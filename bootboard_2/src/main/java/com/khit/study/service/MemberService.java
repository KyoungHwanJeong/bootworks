package com.khit.study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.study.dto.MemberDTO;
import com.khit.study.entity.Member;
import com.khit.study.exception.BootBoardException;
import com.khit.study.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
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

	public MemberDTO findById(Long id) {
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
			
			return memberDTO;
		}else {
			throw new BootBoardException("찾는 데이터가 없습니다.");
		}
	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);
	}
}
