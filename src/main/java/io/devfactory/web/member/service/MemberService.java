package io.devfactory.web.member.service;

import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.member.domain.Member;
import io.devfactory.web.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings({"ClassCanBeRecord", "squid:S112"})
@Transactional(readOnly = true)
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

  @Transactional
  public Long saveMember(Member member) {
    final var savedMember = memberRepository.save(member);
    return savedMember.getId();
  }

  @Transactional
  public Long saveMemberException(Member member) throws Exception {
    final var savedMember = memberRepository.save(member);
    throwException();
    return savedMember.getId();
  }

  @Transactional
  public Long saveMemberRuntimeException(Member member) {
    final var savedMember = memberRepository.save(member);
    throwRuntimeException();
    return savedMember.getId();
  }

  @Transactional
  public Long changeMember(Long id, Member member) {
    final var findMember = findMemberInternal(id);
    findMember.changeMember(member);
    return findMember.getId();
  }

  @Transactional
  public Long changeMemberWithQuery(Long id, Member member) {
    memberRepository.qChangeMember(id, member);
    return id;
  }

  @Transactional
  public Long changeMemberWithQueryException(Long id, Member member) throws Exception {
    memberRepository.qChangeMember(id, member);
    throwException();
    return id;
  }

  @Transactional
  public Long changeMemberWithQueryRuntimeException(Long id, Member member) {
    memberRepository.qChangeMember(id, member);
    throwRuntimeException();
    return id;
  }

  @Transactional
  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }

  @Transactional
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
