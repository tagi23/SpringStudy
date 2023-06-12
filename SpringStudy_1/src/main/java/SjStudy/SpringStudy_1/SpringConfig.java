package SjStudy.SpringStudy_1;

import SjStudy.SpringStudy_1.AOP.TimeTraceAop;
import SjStudy.SpringStudy_1.repository.*;
import SjStudy.SpringStudy_1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록
//XML로 설정하는 방식이 있지만 최근에는 잘 사용하지 않는다.
//DI에는 필드주입,Setter주입,생성자 주입 이렇게 3가지 방법이 있다. 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
//실무에서는 주로 정령화된 컨트롤러,서비스,리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
//정형화가 되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource){  //DI
//        this.dataSource = dataSource;
//    }

    @Bean //빈을 등록
    public MemberService memberService(){  //로직 호출후 스프링 빈에 등록
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){  //memberService에 등록할 소스
//       return new JdbcMemberRepository(dataSource);   //구현체 << 스프링을 쓰는 큰 이유중 하나
//       return new JdbcTemplateMemberRepository(dataSource);
//       return  new JpaMemberRepository(em);  //entitymaneger가 필요함
//
//    }
//
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
