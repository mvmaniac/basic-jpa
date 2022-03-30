package io.devfactory.web.team.dto;

import io.devfactory.web.team.domain.postgresql.Team;
import io.devfactory.web.team.dto.request.TeamCreateRequestView;
import io.devfactory.web.team.dto.request.TeamModifyRequestView;
import io.devfactory.web.team.dto.response.TeamResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = TeamTranslatorMapper.class)
public interface TeamMapper {

  TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

  Team createViewOf(TeamCreateRequestView createRequestView);

  Team modifyViewOf(TeamModifyRequestView modifyRequestView);

  @Mapping(source = "teamType", target = "teamTypeName", qualifiedBy = TeamTypeToValue.class)
  @Mapping(source = "createdBy", target = "createdId")
  TeamResponseView toView(Team team);

  List<TeamResponseView> toList(List<Team> teams);

}
