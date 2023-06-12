package SjStudy.SpringStudy_1.repository;

import SjStudy.SpringStudy_1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    //jpql select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
