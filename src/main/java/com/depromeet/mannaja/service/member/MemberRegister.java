package com.depromeet.mannaja.service.member;

import com.depromeet.mannaja.controller.request.MemberRequest;
import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegister {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(MemberRequest request) {
        Member member = Member.create(request);
        member = memberRepository.save(member);
        return member;
    }
}
