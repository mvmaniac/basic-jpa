package io.devfactory.web.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.devfactory.web.member.domain.mysql.Member;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static io.devfactory.web.member.domain.mysql.QMember.member;

@RequiredArgsConstructor
public class MemberRepositorySupportImpl implements MemberRepositorySupport {

  private final JPAQueryFactory mysqlJpaQueryFactory;

  @Override
  public List<Member> qFindAll() {
    return mysqlJpaQueryFactory.selectFrom(member).fetch();
  }

  @Override
  public void qChangeMember(Long id, Member changedMember) {
    // @formatter:off
    mysqlJpaQueryFactory
      .update(member)
        .set(member.email, changedMember.getEmail())
        .set(member.username, changedMember.getUsername())
        .set(member.password, changedMember.getPassword())
        .set(member.birthDay, changedMember.getBirthDay())
        .set(member.alarmDate, changedMember.getAlarmDate())
        .set(member.grade, changedMember.getGrade())
        .set(member.useYn, changedMember.getUseYn())
        .set(member.updatedDate, LocalDateTime.now())
      .where(member.id.eq(id))
      .execute();
    // @formatter:on
  }

  @Override
  public void qDeleteById(Long id) {
    // @formatter:off
    mysqlJpaQueryFactory
      .delete(member)
      .where(member.id.eq(id))
      .execute();
    // @formatter:on
  }

}
