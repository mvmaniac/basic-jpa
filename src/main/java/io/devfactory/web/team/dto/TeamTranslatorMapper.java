package io.devfactory.web.team.dto;

import io.devfactory.web.team.domain.TeamType;
import org.mapstruct.Mapper;

@Mapper
public interface TeamTranslatorMapper {

  @TeamTypeToValue
  static String ofValue(TeamType teamType) {
    return teamType.getValue();
  }

}
