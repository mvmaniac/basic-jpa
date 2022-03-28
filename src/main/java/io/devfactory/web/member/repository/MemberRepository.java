package io.devfactory.web.member.repository;

import io.devfactory.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport {

}
