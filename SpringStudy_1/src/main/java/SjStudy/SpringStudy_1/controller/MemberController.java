package SjStudy.SpringStudy_1.controller;

import SjStudy.SpringStudy_1.domain.Member;
import SjStudy.SpringStudy_1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//스프링 빈을 등록하는 2가지 방법
// 1: 컴포넌트 스캔과 자동 의존관계 설정 (싱글톤 : 유일하게 하나만 등록해서 공유)
// 2: 자바 코드로 직접 스프링 빈 등록

@Controller    //spring 컨테이너에  @Controller라는 어노테이션이 있으면 MemberController를 객체를 생성해서 컨테이너가 가지고있음 (컴포넌트 스캔)
public class MemberController {
    private MemberService memberService;

    @Autowired  //스프링 컨테이너에 자동으로 등록(연결)     DI 주입  자동 의존관계 설정 , 컨테이너에 올라가있는 경우만 Autowired가 적용
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        System.out.printf("member = " + member.getName());
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
