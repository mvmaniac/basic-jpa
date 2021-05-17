package io.devfactory.web.member.dto.request;

import io.devfactory.web.member.domain.Member;
import io.devfactory.web.member.dto.MemberMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberCreateRequestView {

  private final String email;
  private final String username;
  private final String password;
  private final LocalDate birthDay;
  private final LocalDateTime alarmDate;

  @Builder
  private MemberCreateRequestView(String email, String username, String password,
      LocalDate birthDay, LocalDateTime alarmDate) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.birthDay = birthDay;
    this.alarmDate = alarmDate;
  }

  public Member toMember() {
    return MemberMapper.INSTANCE.createViewOf(this);
  }

}
