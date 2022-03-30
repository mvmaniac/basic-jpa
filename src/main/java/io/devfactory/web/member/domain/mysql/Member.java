package io.devfactory.web.member.domain.mysql;

import io.devfactory.global.constant.Yn;
import io.devfactory.global.model.BaseTimeEntity;
import io.devfactory.web.member.domain.GardeConvertor;
import io.devfactory.web.member.domain.Grade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.devfactory.global.constant.Yn.Y;
import static io.devfactory.web.member.domain.Grade.NORMAL;
import static java.util.Objects.nonNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;


@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_member")
@Entity
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column
  private String email;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private LocalDate birthDay;

  @Column
  private LocalDateTime alarmDate;

  @Convert(converter = GardeConvertor.class)
  @Column
  private Grade grade;

  @Enumerated(STRING)
  @Column
  private Yn useYn;

  // foreignKey = @ForeignKey(NO_CONSTRAINT) 로 외래키를 지정 하지 않을 수 있음
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "created_by", updatable = false)
  private Member createdBy;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "updated_by")
  private Member updatedBy;

  @Builder
  private Member(String email, String username, String password, LocalDate birthDay,
      LocalDateTime alarmDate, Grade grade, Yn useYn) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.birthDay = birthDay;
    this.alarmDate = alarmDate;
    this.grade = nonNull(grade) ? grade : NORMAL;
    this.useYn = nonNull(useYn) ? useYn : Y;
    this.createdBy = this;
    this.updatedBy = this;
  }

  // TODO: 임시 회원 만들기, 로그인 처리가 된다면 지워야 함
  public static Member of(Long id) {
    final var member = Member.builder().build();
    member.id = id;
    return member;
  }

  // TODO: 인증을 통한 updatedBy 수정 해보기
  public void changeMember(Member member) {
    this.email = member.getEmail();
    this.username = member.getUsername();
    this.password = member.getPassword();
    this.password = member.getPassword();
    this.birthDay = member.getBirthDay();
    this.alarmDate = member.getAlarmDate();
    this.grade = member.getGrade();
    this.useYn = member.getUseYn();
  }

}
