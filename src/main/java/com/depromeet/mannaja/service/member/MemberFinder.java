package com.depromeet.mannaja.service.member;

import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberFinder {
    @Autowired
    private MemberRepository memberRepository;

    public Member getMember(Long memberId){
        return memberRepository.findById(memberId)
                .orElse(new Member());
    }

    public Member getMemberByUuid(String uuid) {
        return memberRepository.findByUuid(uuid)
                .orElse(new Member());
    }
}
