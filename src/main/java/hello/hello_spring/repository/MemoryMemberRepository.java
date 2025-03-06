package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

// HashMap을 사용하여 메모리에 회원 정보를 저장하는 **임시 저장소**
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 회원 정보를 저장하는 Map (Key: 회원 ID, Value: Member 객체)
    private static long sequence = 0L;// 회원 ID를 생성하기 위한 변수

    /**
     * 회원 저장 메서드
     * - 회원 객체에 자동으로 ID 값을 할당하고 저장소(Map)에 저장
     *
     * @param member 저장할 회원 객체
     * @return 저장된 회원 객체
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 증가시킨 시퀀스값을 회원 id로 설정
        store.put(member.getId(), member); //map에 회원 저장
        return member; // 저장된 회원 반환
    }


    /**
     * ID로 회원 찾기
     * - ID를 기반으로 저장된 회원을 찾아 반환
     *
     * @param id 찾을 회원의 ID
     * @return Optional<Member>: 해당 ID의 회원이 존재하면 반환, 없으면 Optional.empty()
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * 이름으로 회원 찾기
     * - 저장된 회원 목록을 검색하여 특정 이름과 일치하는 회원을 찾음
     *
     * @param name 찾을 회원의 이름
     * @return Optional<Member>: 해당 이름의 회원이 존재하면 반환, 없으면 Optional.empty()
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store의 모든 회원을 stream()으로 변환->간결
                .filter(member -> member.getName().equals(name)) // 이름이 같은 회원 찾기
                .findAny(); // 첫 번째로 찾은 회원을 반환
    }


    /**
     * 저장된 모든 회원 조회
     *
     * @return List<Member>: 저장된 모든 회원 목록
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 모든 회원을 리스트 형태로 반환
    }

    /**
     * 저장소 초기화
     * - 테스트 시 저장된 회원 데이터를 모두 삭제하는 용도로 사용
     */
    public void clearStore() {
        store.clear();
    }
}