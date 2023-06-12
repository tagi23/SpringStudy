package SjStudy.SpringStudy_1.service;

import SjStudy.SpringStudy_1.domain.Member;
import SjStudy.SpringStudy_1.repository.MemberRepository;
import SjStudy.SpringStudy_1.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {    //단위테스트   하나하나 쪼개서 테스트를 하는것이 더 좋은경우가 많다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach  //실행하기 전에
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);   //같은 MemoryMemberRepository를 사용한다.  (DI : 의존관계(관계를 외부에서 넣어줌))
    }

    @AfterEach
    public void afterEach(){    //테스트 데이터 초기화
        memberRepository.clearStroe();
    }

    @Test
    void 회원가입() {
        //given    무언가를 주고
        Member member = new Member();
        member.setName("Spring");
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

    @Test
    void 전체회원조회() {
    }

    @Test
    void findOne() {
    }
}