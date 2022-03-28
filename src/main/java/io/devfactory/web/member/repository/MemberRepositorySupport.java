package io.devfactory.web.member.repository;

import io.devfactory.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositorySupport {

  List<Member> qFindAll();

  void qChangeMember(Long id, Member changedMember);

  void qDeleteById(Long id);

}
