package io.devfactory.web.member.dto.request;

import io.devfactory.global.constant.Yn;
import io.devfactory.web.member.domain.mysql.Member;
import io.devfactory.web.member.dto.MemberMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("ClassCanBeRecord")
@Getter
public class MemberModifyRequestView {

  private final String email;
  private final String username;
  private final String password;
  private final LocalDate birthDay;
  private final LocalDateTime alarmDate;
  private final String grade; // String -> Enum 유효성 체크는 다른 예제 참고
  private final Yn useYn;

  @Builder
  private MemberModifyRequestView(String email, String username, String password,
      LocalDate birthDay, LocalDateTime alarmDate, String grade, Yn useYn) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.birthDay = birthDay;
    this.alarmDate = alarmDate;
    this.grade = grade;
    this.useYn = useYn;
  }

  public Member toMember() {
    return MemberMapper.INSTANCE.modifyViewOf(this);
  }

}
