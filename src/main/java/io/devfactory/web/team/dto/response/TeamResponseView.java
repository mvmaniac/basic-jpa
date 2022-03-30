package io.devfactory.web.team.dto.response;

import io.devfactory.global.constant.Yn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@SuppressWarnings("ClassCanBeRecord")
@Getter
public class TeamResponseView {

  private final Long id;
  private final String teamType;
  private final String teamTypeName;
  private final String title;
  private final String description;
  private final Yn useYn;
  private final String createdId;
  private final LocalDateTime createdDate;

  @Builder
  public TeamResponseView(Long id, String teamType, String teamTypeName, String title,
      String description, Yn useYn, String createdId, LocalDateTime createdDate) {
    this.id = id;
    this.teamType = teamType;
    this.teamTypeName = teamTypeName;
    this.title = title;
    this.description = description;
    this.useYn = useYn;
    this.createdId = createdId;
    this.createdDate = createdDate;
  }

}
