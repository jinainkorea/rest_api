package com.example.demo.Member.controller;

import com.example.demo.Member.dto.MemberDTO;
import com.example.demo.Member.entity.Member;
import com.example.demo.Member.request.MemberRequest;
import com.example.demo.Member.service.MemberService;
import com.example.demo.global.RsData.RsData;
import com.example.demo.global.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Tag(name="ApiV1Controller", description = "회원 인증인가 API")
public class ApiV1MemberController {
    private final MemberService memberService;

    private final JwtProvider jwtProvider;

    @PostMapping("/join")
    public RsData join(@Valid @RequestBody MemberRequest memberRequest) {
        String username = memberRequest.getUsername();
        String password = memberRequest.getPassword();
        Member member = this.memberService.join(username, password);
        if (member == null) {
            return RsData.of("400", "회원가입에 실패하였습니다.");
        }
        return RsData.of("200", "회원가입에 성공 했습니다.", new MemberDTO(member));
    }

//    @PostMapping("/login")
//    public RsData login(@Valid @RequestBody MemberRequest memberRequest, HttpSession httpSession) {
//        Member member = this.memberService.getMemberByName(memberRequest.getUsername());
//        if (member == null) {
//            return RsData.of("400", "존재하지 않는 회원입니다.");
//        }
//        httpSession.setAttribute("USER ID", member.getUsername());
//        return RsData.of("200", "로그인 성공", new MemberDTO(member));
//    }

//    JWT 토큰의 인증, 인가 사용
    @PostMapping("/login")
    public String login (@Valid @RequestBody MemberRequest memberRequest, HttpServletResponse httpServletResponse) {
        Member member = this.memberService.getMemberByName(memberRequest.getUsername());
        String jwtToken = this.jwtProvider.genToken(member, 5);

        httpServletResponse.addCookie(new Cookie("loginToken", jwtToken));

        return jwtToken;
    }

    @GetMapping("/me")
    public String me (HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String accessToken = "";
        for (Cookie cookie : cookies) {
            if ("loginToken".equals(cookie.getName())) {
                accessToken = cookie.getValue();
            }
        }

        boolean checkedToken = jwtProvider.verify(accessToken);

        System.out.println(checkedToken);
        if (!checkedToken) {
            return "유효성 검증 실패";
        }

        Map<String, Object> claims = jwtProvider.getClaims(accessToken);
        claims.get("id");
        claims.get("username");

        return (String) claims.get("username");
    }

    @GetMapping("/logout")
    public RsData logout(HttpSession httpSession) {
        if (httpSession.getAttribute("USER ID") == null) {
            return RsData.of("400", "이미 로그아웃 상태입니다.");
        }
        httpSession.invalidate();
        return RsData.of("200", "로그아웃 성공");
    }
}
