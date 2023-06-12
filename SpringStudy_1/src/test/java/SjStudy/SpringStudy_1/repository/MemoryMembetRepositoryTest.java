package SjStudy.SpringStudy_1.repository;

import SjStudy.SpringStudy_1.domain.Member;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMembetRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();  //테스트할 객체 생성

    @AfterEach
    public void afterEach(){    //테스트 데이터 초기화
        repository.clearStroe();
    }
    // 번외로 테스트코드(틀)를 먼저 만들고 이후에 구현 클래스를 만드는것 : TDD
    //테스트가 많아질 경우 나중에 빌드하거나 gradlew를 띄우고 test를한다.
    @Test
    public void save(){
        Member member = new Member();  //테스트용 객체 생성
        member.setName("김성진");

        repository.save(member);  //save 메서드 실행

        Member result = repository.findById(member.getId()).get();  //id 값을 찾고 리턴값을 get으로 불러온다.
       // Assertions.assertEquals(member , null);   //junit 라이브러리의 테스트
        assertThat(member).isEqualTo(result);  //assertj 라이브러리 테스트
    }

    @Test  //테스트 어노테이션 생성
    public void findByName(){
        Member member1 = new Member();
        member1.setName("김진수");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("김철수");
        repository.save(member2);

        Member result =  repository.findByName("김진수").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("김진수");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("김철수");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("김철수");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }


}
