package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    /**
     * 회원 등록 폼을 보여주는 메서드
     * GET 요청이 "/members/new"로 들어오면 members/createMemberForm.html을 반환
     *
     * @return "members/createMemberForm" - 회원 가입 폼 뷰 반환
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; // templates/members/createMemberForm.html을 찾아서 반환
    }

    /**
     * 회원 가입을 처리하는 메서드
     * POST 요청이 "/members/new"로 들어오면 폼 데이터를 받아 회원을 생성
     *
     * @param form - 사용자가 입력한 회원 정보 (MemberForm 객체)
     * @return "redirect:/" - 회원 가입 후 홈 화면으로 리다이렉트
     */
    @PostMapping(value = "/members/new") //주로 postmapping은 data를 폼에넣어서 전달 /getmapping은 조회시 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // MemberForm에서 입력받은 이름을 Member 객체에 설정

        memberService.join(member);

        return "redirect:/"; // 홈 화면("/")으로 리다이렉트
    }


    /**
     * 회원 목록을 조회하는 메서드
     * GET 요청이 "/members"로 들어오면 회원 목록을 가져와 화면에 표시
     *
     * @param model - 뷰에 데이터를 전달하기 위한 Model 객체
     * @return "members/memberList" - 회원 목록을 보여주는 뷰 반환
     */

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 모든 회원 목록을 가져옴
        model.addAttribute("members", members); // 모델에 "members"라는 이름으로 회원 목록을 추가하여 뷰로 전달
        return "members/memberList"; // 회원 목록을 표시할 템플릿 반환 (templates/members/memberList.html)
    }




}
