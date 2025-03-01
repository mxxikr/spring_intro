package com.mxxikr.springintro.controller;

import com.mxxikr.springintro.domain.Member;
import com.mxxikr.springintro.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

// @Controller 어노테이션을 사용하면 스프링 컨테이너에 컨트롤러로 등록
@Controller
public class MemberController {
    private final MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 등록 폼 화면으로 이동
     * @return
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * 회원 등록
     * @param form
     * @return
     */
    @PostMapping("/members/new") // 데이터 등록 시 PostMapping 사용
    public String create(MemberForm form) throws SQLException {
        Member member = new Member();
        member.setName(form.getName()); // 회원 이름 설정
        memberService.join(member);
    return "redirect:/"; // 홈 화면으로 리다이렉트
    }

    /**
     * 회원 목록 조회
     * 회원 목록을 리스트 형태로 전체 조회해서 model에 담아 뷰로 전달
     * @param model
     * @return
     */
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList"; // 회원 목록 화면으로 이동
    }
}
