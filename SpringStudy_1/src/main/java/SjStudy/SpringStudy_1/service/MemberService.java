package SjStudy.SpringStudy_1.service;

import SjStudy.SpringStudy_1.domain.Member;
import SjStudy.SpringStudy_1.repository.MemberRepository;
import SjStudy.SpringStudy_1.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

// @Service  //스프링이 올라올때 인식하는 어노테이션
@Transactional //jpa를 사용할때는 항상 트랙잭션이 있어야함
public class MemberService {
    private final MemberRepository memberRepository;

    // @Autowired //연결 DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join (Member member){
            //동명이인일시 회원가입 x
            validateduplicateMember(member);
            //회원가입
            memberRepository.save(member);
            return member.getId();
    }

    private void validateduplicateMember(Member member) {  //중복회원 검증 메소드
        memberRepository.findByName(member.getName())   //저장할때 findByName(member.getName())으로 똑같은 이름이 찾는로직
            .ifPresent(m ->{    //값이 있으면? m에 값을 준다.
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){ //전체 회원 조회
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){ //하나의 회원 조회
        return memberRepository.findById(memberId);
    }
}
