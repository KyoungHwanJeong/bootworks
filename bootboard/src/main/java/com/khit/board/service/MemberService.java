package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor - 이때는 final를 안 붙인다
@RequiredArgsConstructor	//생성자 주입 방식 - final을 붙인다
@Service
public class MemberService {

	private final MemberRepository memberRepository;
/*	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = new memberRepository();
	}	// 생성자 주입 방식
*/

	public void save(MemberDTO memberDTO) {
		
		//db안으로 저장(entity를 저장해야 함)
		//dto(memberDTO)를 entity(member)로 변환 메서드 필요
		Member member = Member.toSaveEntity(memberDTO);
		
		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		//db에서 member entity를 꺼내와서 
		List<Member> memberList = memberRepository.findAll();
		//변환 메서드가 필요
		//member를 담을 빈 dto 리스트를 생성한다
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for(Member member: memberList) {
			//memberList를 반복하면서
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
			
		//컨트롤러에 DTOList로 보낸다
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		//db 에서 member 1개 꺼내오기
		//findById(id).get()
		//memberRepository.findById(id).get() = .get()으로 1개만 받아온다
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
			//entity -> dto로 변환한다
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
			
			return memberDTO;
		}else {
			throw new BootBoardException("찾는 데이터가 없습니다.");	//없는 id 페이지 작동시
		}
	}

	public void deleteById(Long id) {
		//삭제 - deleteById(id)
		memberRepository.deleteById(id);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		//1. 이메일로 회원 조회(일반적으로 이메일과 비밀번호를 비교)
		Optional<Member> memberEmail = 
			memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		
		if(memberEmail.isPresent()) {
			//조회 결과로 이메일이 있음 - 1건 가져오기
			Member member = memberEmail.get();
			//비밀 번호 일치
			if(member.getMemberPassword().equals(memberDTO.getMemberPassword())){
				//entity -> dto로 변환한다
				MemberDTO dto = MemberDTO.toSaveDTO(member);
				return dto;
			}else {
				//비밀번호 불일치
				return null;
			}
		}else {
			return null;
		}
	}

	//수정 페이지 가져오기
	public MemberDTO findByMemberEmail(String email) {
		//db에 있는 이메일ㄹ로 검색한 회원 객체를 가져오고
		// id가 없을 때 오류 처리 - "url을 찾을 수 없습니다"
		Member member = memberRepository.findByMemberEmail(email).get();
		//회원 객체(entity)를 dto로 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		//save()가 가입, 수정되는데 가입할 때는 id가 없음
		Member member = Member.toUpdateEntity(memberDTO);
		//id가 있는 엔티티의 메서드가 필요하다
		memberRepository.save(member);
	}

	//유효성 검사
	public String checkEmail(String memberEmail) {
		//db에 있는 이메일을 조회해서 있으면 "OK"문자를 보내고 없으면 "NO"를 보낸다
		Optional<Member> findmember = memberRepository.findByMemberEmail(memberEmail);
		if(findmember.isEmpty()) {//db에 회원이 없으면 가입해도 되는 "OK" 문자를 반환한다
			return "OK";
		}else {
			return "NO";
		}
	}
	

}
