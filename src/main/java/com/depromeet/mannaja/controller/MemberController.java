package com.depromeet.mannaja.controller;

import com.depromeet.mannaja.entity.Member;
import com.depromeet.mannaja.service.member.MemberFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController{
    @Autowired
    private MemberFinder memberFinder;

    @GetMapping("/{memberId")
    public Member geMember(Long memberId){
        Optional<Member> member = memberFinder.getMember(memberId);
        if(member.isPresent()){
            return member.get();
        }else{
            ///redirect 회원가입
            return null;
        }
    }
}
