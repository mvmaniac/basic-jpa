package io.devfactory.web.member.service;

import io.devfactory.global.common.annotation.MysqlTransaction;
import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.member.domain.mysql.Member;
import io.devfactory.web.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"squid:S112"})
@MysqlTransaction(readOnly = true)
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public List<Member> findMembersWithQuery() {
    return memberRepository.qFindAll();
  }

  public Member findMember(Long id) {
    return findMemberInternal(id);
  }

  @MysqlTransaction
  public Long saveMember(Member member) {
    final var savedMember = memberRepository.save(member);
    return savedMember.getId();
  }

  @MysqlTransaction
  public Long saveMemberException(Member member) throws Exception {
    final var savedMember = memberRepository.save(member);
    throwException();
    return savedMember.getId();
  }

  @MysqlTransaction
  public Long saveMemberRuntimeException(Member member) {
    final var savedMember = memberRepository.save(member);
    throwRuntimeException();
    return savedMember.getId();
  }

  @MysqlTransaction
  public Long changeMember(Long id, Member member) {
    final var foundMember = findMemberInternal(id);
    foundMember.changeMember(member);
    return foundMember.getId();
  }

  @MysqlTransaction
  public Long changeMemberWithQuery(Long id, Member member) {
    memberRepository.qChangeMember(id, member);
    return id;
  }

  @MysqlTransaction
  public Long changeMemberWithQueryException(Long id, Member member) throws Exception {
    memberRepository.qChangeMember(id, member);
    throwException();
    return id;
  }

  @MysqlTransaction
  public Long changeMemberWithQueryRuntimeException(Long id, Member member) {
    memberRepository.qChangeMember(id, member);
    throwRuntimeException();
    return id;
  }

  @MysqlTransaction
  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }

  @MysqlTransaction
  public void deleteMemberWithQuery(Long id) {
    memberRepository.qDeleteById(id);
  }

  private Member findMemberInternal(Long id) {
    return memberRepository.findById(id).orElseThrow(ServiceRuntimeException::new);
  }

  private void throwException() throws Exception {
    throw new Exception("mysql 에러 발생");
  }

  private void throwRuntimeException() {
    throw new ServiceRuntimeException("mysql 런타임 에러 발생");
  }

}
