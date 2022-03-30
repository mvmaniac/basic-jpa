package io.devfactory.web.member.repository;

import io.devfactory.web.member.domain.mysql.Member;

import java.util.List;

public interface MemberRepositorySupport {

  List<Member> qFindAll();

  void qChangeMember(Long id, Member changedMember);

  void qDeleteById(Long id);

}
