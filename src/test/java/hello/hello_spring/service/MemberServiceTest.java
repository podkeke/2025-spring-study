package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트 실행 전에 호출. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello"); // "hello"라는 이름을 가진 회원 생성
        //When;회원가입 실행
        Long saveId = memberService.join(member); // 회원 가입 후 반환된 회원 ID
        //Then;가입된 회원을 조회해서 이름이 맞는지 확인
        Member findMember = memberRepository.findById(saveId).get(); // 저장된 회원 조회
        assertEquals(member.getName(), findMember.getName()); // 이름이 같은지 확인
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given ;중복된 이름 가진 두회원 생성
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When;천번쨰 회원가입
        memberService.join(member1);
        //두번째 회원가입시 예외가 발생해야함
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        //Then;예외 메시지가 "이미 존재하는 회원입니다."인지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}