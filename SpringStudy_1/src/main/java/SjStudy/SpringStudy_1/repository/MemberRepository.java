package SjStudy.SpringStudy_1.repository;

import SjStudy.SpringStudy_1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {   //도메인을 저장할 인터페이스
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
