package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {


    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //MemberService가 MemoryMemberRepository에 강하게 의존하게 되어, 다른 구현체로 쉽게 변경하거나 테스트하기 어려운 구조가 된다.
    //->
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입 기능
     * @param member : 가입할 회원 객체
     * @return : 가입한 회원의 ID
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); //회원 저장
        return member.getId(); //저장된 회원의 id 반환
    }

    /**
     * 중복 회원 검증
     * 같은 이름의 회원이 이미 존재하면 예외를 발생시킴
     * @param member : 중복 여부를 확인할 회원 객체
     */
    private void validateDuplicateMember(Member member) {
        // 같은 이름의 회원이 있는지 확인
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //값이 존재하면?
                    // 이미 존재하면 예외 발생 -> 실행x
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 모든 회원 조회
     * @return : 저장된 모든 회원 목록
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 모든 회원 리스트 반환
    }

    /**
     * 회원 ID로 회원 조회
     * @param memberId : 조회할 회원의 ID
     * @return : 회원 객체
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); //id로 회원 조회
    }
}