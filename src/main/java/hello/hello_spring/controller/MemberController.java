package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /**필드주입
    @Autowired private MemberService memberService;
    */


    /**새터주입
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */


    //생성자 주입
    private final MemberService memberService;

    @Autowired //스프링컨테이너에 있는 멤버 서비스를 가져다가 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
