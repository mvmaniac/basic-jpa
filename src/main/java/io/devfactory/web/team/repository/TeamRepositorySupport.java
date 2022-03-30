package io.devfactory.web.team.repository;

import io.devfactory.web.team.domain.postgresql.Team;

import java.util.List;

public interface TeamRepositorySupport {

  List<Team> qFindAll();

  void qChangeTeam(Long id, Team changeTeam);

  void qDeleteById(Long id);

}
