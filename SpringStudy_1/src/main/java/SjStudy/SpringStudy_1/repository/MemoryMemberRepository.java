package SjStudy.SpringStudy_1.repository;

import SjStudy.SpringStudy_1.domain.Member;
import org.apache.catalina.Store;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

// @Repository  //스프링이 Repository를 인식
public class MemoryMemberRepository implements MemberRepository{   //메모리에 memberrepository에 저장하는 클래스
    private static Map<Long,Member> store = new HashMap<>();  //공유되는 문제일때는 컨커럿해쉬맵을 씀
    private static long sequence = 0L;    //실무에서는 어텀롱을 씀 동시성 문제로   구문용 id를 1식 증가
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {  //id찾기
        return Optional.ofNullable(store.get(id));  //찾아도 없는 경우 널로 반환 있으면 id 반환
    }

    @Override
    public Optional<Member> findByName(String name) {  //이름 찾기
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();  //1개라도 찾는것

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStroe(){   //테스트용 초기화
        store.clear();
    }
}
