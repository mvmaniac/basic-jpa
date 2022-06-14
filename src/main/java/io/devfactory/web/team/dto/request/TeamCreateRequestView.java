package io.devfactory.web.team.dto.request;

import io.devfactory.web.team.domain.postgresql.Team;
import io.devfactory.web.team.domain.TeamType;
import io.devfactory.web.team.dto.TeamMapper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamCreateRequestView {

  private final String title;
  private final String description;
  private final TeamType teamType;

  @Builder
  private TeamCreateRequestView(String title, String description, TeamType teamType) {
    this.title = title;
    this.description = description;
    this.teamType = teamType;
  }

  public Team toTeam() {
    return TeamMapper.INSTANCE.createViewOf(this);
  }

}
