package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    /**
     * 각 테스트가 끝난 후 실행되는 메서드
     * 저장소를 비워서 테스트 간 데이터가 겹치지 않도록 함->테스트순서 보장 못해서
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then; 저장한 회원의 ID로 다시 조회한 후, 동일한 객체인지 확인
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when ;저장된 회원 중 "spring1"이라는 이름을 가진 회원 찾기
        Member result = repository.findByName("spring1").get();
        //then ;찾은 회원이 member1과 동일한지 검증
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when ;저장소에 있는 모든 회원을 조회
        List<Member> result = repository.findAll();
        //then ;저장된 회원 수가 2명인지 확인
        assertThat(result.size()).isEqualTo(2);
    }
}