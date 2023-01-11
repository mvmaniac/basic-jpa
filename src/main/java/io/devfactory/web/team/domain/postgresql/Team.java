package io.devfactory.web.team.domain.postgresql;

import io.devfactory.global.constant.Yn;
import io.devfactory.global.model.BaseTimeEntity;
import io.devfactory.web.team.domain.TeamType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static io.devfactory.global.constant.Yn.Y;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;


@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_team")
@Entity
public class Team extends BaseTimeEntity {

  @Id
  @SequenceGenerator(name = "seq_team_gen", sequenceName = "seq_team", allocationSize = 1)
  @GeneratedValue(strategy = SEQUENCE, generator = "seq_team_gen")
  private Long id;

  @Column
  private String title;

  @Column
  private String description;

  @Enumerated(STRING)
  @Column
  private TeamType teamType;

  @Enumerated(STRING)
  @Column
  private Yn useYn;

  @Column(updatable = false)
  private String createdBy;

  @Column
  private String updatedBy;

  @Builder
  private Team(String title, String description, TeamType teamType, String createdBy) {
    this.title = title;
    this.description = description;
    this.teamType = teamType;
    this.useYn = Y;
    this.createdBy = createdBy;
    this.updatedBy = createdBy;
  }

  // TODO: 임시 등록자, 수정자 만들기, 로그인 처리가 된다면 지워야 함
  public void updateCreatedByAndUpdatedBy() {
    this.createdBy = "system";
    this.updatedBy = "system";
  }

  public void changeTeam(Team team) {
    this.title = team.getTitle();
    this.description = team.getDescription();
    this.useYn = team.getUseYn();
  }

}
