package io.devfactory.web.member.repository;

import io.devfactory.global.common.annotation.MysqlRepository;
import io.devfactory.web.member.domain.mysql.Member;
import org.springframework.data.jpa.repository.JpaRepository;

@MysqlRepository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport {

}
