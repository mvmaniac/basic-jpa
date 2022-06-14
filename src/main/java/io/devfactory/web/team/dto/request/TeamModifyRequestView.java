package io.devfactory.web.team.dto.request;

import io.devfactory.global.constant.Yn;
import io.devfactory.web.team.domain.postgresql.Team;
import io.devfactory.web.team.domain.TeamType;
import io.devfactory.web.team.dto.TeamMapper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamModifyRequestView {

  private final String title;
  private final String description;
  private final TeamType teamType;
  private final Yn useYn;

  @Builder
  private TeamModifyRequestView(String title, String description, TeamType teamType, Yn useYn) {
    this.title = title;
    this.description = description;
    this.teamType = teamType;
    this.useYn = useYn;
  }

  public Team toTeam() {
    return TeamMapper.INSTANCE.modifyViewOf(this);
  }

}
