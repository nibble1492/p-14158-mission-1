package com.back.domain.member.member.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        return memberRepository.save(
                new Member(username, password, nickname)
        );
    }
}