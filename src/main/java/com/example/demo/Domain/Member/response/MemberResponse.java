package com.example.demo.Domain.Member.response;

import com.example.demo.Domain.Member.entity.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberResponse {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public MemberResponse(Member member) {
        this.username = member.getUsername();
        this.password = member.getPassword();
    }
}
