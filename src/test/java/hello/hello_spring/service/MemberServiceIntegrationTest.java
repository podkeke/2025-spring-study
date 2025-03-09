package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional//롤백;디비에있는데이터정리됨 굳~
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("spring"); // "spring"라는 이름을 가진 회원 생성
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