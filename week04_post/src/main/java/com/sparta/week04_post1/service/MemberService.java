package com.sparta.week04_post1.service;

//(2) service --> (3) repository
//MemberController(1) 와 유사

import com.sparta.week04_post1.dto.MemberResponseDto;
import com.sparta.week04_post1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.week04_post1.util.SecurityUtil.getCurrentMemberId;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }


// 현재 SecurityContext(내부메모리) 에 있는 유저 정보 가져오기
    //** Spring Security > SecurityContextHolder > SecurityContext > Authentication(principal, credential)
    //** Spring Security : 인증이 완료되면 아이디, 패스워드를 가진 사용자의 principal 과 credential 정보를 Authentication 에 담는다.
    //** SecurityContext(내부메모리) : API 요청이 들어오면 필터에서 Access Token 을 복호화 해서 유저 정보를 꺼내 이곳에 저,
    //                                이곳에 저장된 유저 정보는 전역으로 어디서든 꺼낼 수 있음
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
                                    //** SecurityUtil : 유저 정보에서 Member ID 만 반환하는 메소드가 정의되어 있음
                                    //** SecurityUtil.getCurrentMemberId() : 내 정보를 가져올 때 사용
        return memberRepository.findById(getCurrentMemberId())
                .map(MemberResponseDto::of)
                //** orElseThrow : optional 인자가 null 일 경우 예외처리
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

}