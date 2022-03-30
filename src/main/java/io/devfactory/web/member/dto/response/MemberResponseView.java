package io.devfactory.web.member.dto.response;

import io.devfactory.global.constant.Yn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@SuppressWarnings("ClassCanBeRecord")
@Getter
public class MemberResponseView {
  
  public final Long id;
  public final String email;
  public final String username;
  public final String grade;
  public final Yn useYn;
  public final LocalDateTime createdDate;
  public final LocalDateTime updatedDate;

  @Builder
  private MemberResponseView(Long id, String email, String username, String grade, Yn useYn,
      LocalDateTime createdDate, LocalDateTime updatedDate) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.grade = grade;
    this.useYn = useYn;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }

}
