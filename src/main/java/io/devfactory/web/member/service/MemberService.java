package io.devfactory.web.member.service;

import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.member.domain.Member;
import io.devfactory.web.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

  public Member findMember(Long id) {
    return findMemberInternal(id);
  }

  @Transactional
  public Long saveMember(Member member) {
    final var savedMember = memberRepository.save(member);
    return savedMember.getId();
  }

  @Transactional
  public Long changeMember(Long id, Member member) {
    final var findMember = findMemberInternal(id);
    findMember.changeMember(member);
    return findMember.getId();
  }

  @Transactional
  public void deleteMember(Long id) {
    memberRepository.deleteById(id);
  }

  private Member findMemberInternal(Long id) {
    return memberRepository.findById(id).orElseThrow(ServiceRuntimeException::new);
  }

}
