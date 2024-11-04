package com.example.demo.Member.service;

import com.example.demo.Member.entity.Member;
import com.example.demo.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String username, String password) {
        Member member = new Member().builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        this.memberRepository.save(member);
        return member;
    }

    public Member getMemberByName(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            return null;
        }
        return member.get();
    }
}
