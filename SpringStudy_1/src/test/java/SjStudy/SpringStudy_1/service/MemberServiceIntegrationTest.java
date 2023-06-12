package SjStudy.SpringStudy_1.service;


import SjStudy.SpringStudy_1.domain.Member;
import SjStudy.SpringStudy_1.repository.MemberRepository;
import SjStudy.SpringStudy_1.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest   //스프링부트 테스트
@Transactional  //트랜잭션후에 마지막에 롤백 해준다.
public class MemberServiceIntegrationTest {   //통합테스트

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
//    @Commit //이 어노테이션은 끝나고 커밋을함
    void 회원가입() {
        //given    무언가를 주고
        Member member = new Member();
        member.setName("Spring100");
        //when     검증(실행)하고
        Long saveId = memberService.join(member);

        //then     결과를 도출한다  ex: 우리가 저장한게 repository에 저장된게 맞는지 확인
        Member findMember=memberService.findOne(saveId).get();    //저장됐으면 findone 로직으로 정보를 findMember에 저장한다.
        assertThat(member.getName()).isEqualTo(findMember.getName());  //저장한이름이 같은지 테스트
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외 발생시
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");  //예외 메시지가 맞는지
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalAccessError e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }

}
