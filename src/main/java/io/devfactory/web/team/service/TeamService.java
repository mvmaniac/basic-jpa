package io.devfactory.web.team.service;

import io.devfactory.global.common.annotation.PostgresqlTransaction;
import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.team.domain.postgresql.Team;
import io.devfactory.web.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"ClassCanBeRecord", "squid:S112"})
@PostgresqlTransaction(readOnly = true)
@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public List<Team> findTeams() {
    return teamRepository.findAll();
  }

  public List<Team> findTeamsWitQuery() {
    return teamRepository.qFindAll();
  }

  public Team findTeam(Long id) {
    return findTeamInternal(id);
  }

  @PostgresqlTransaction
  public Long createTeam(Team team) {
    team.updateCreatedByAndUpdatedBy();
    final var savedTeam = teamRepository.save(team);
    return savedTeam.getId();
  }

  @PostgresqlTransaction
  public Long createTeamException(Team team) throws Exception {
    team.updateCreatedByAndUpdatedBy();
    final var savedTeam = teamRepository.save(team);
    throwException();
    return savedTeam.getId();
  }

  @PostgresqlTransaction
  public Long createTeamRuntimeException(Team team) {
    team.updateCreatedByAndUpdatedBy();
    final var savedTeam = teamRepository.save(team);
    throwRuntimeException();
    return savedTeam.getId();
  }

  @PostgresqlTransaction
  public Long changeTeam(Long id, Team team) {
    final var foundTeam = findTeamInternal(id);
    foundTeam.changeTeam(team);
    return foundTeam.getId();
  }

  @PostgresqlTransaction
  public Long changeTeamWithQuery(Long id, Team team) {
    team.updateCreatedByAndUpdatedBy();
    teamRepository.qChangeTeam(id, team);
    return id;
  }

  @PostgresqlTransaction
  public Long changeTeamWithQueryException(Long id, Team team) throws Exception {
    team.updateCreatedByAndUpdatedBy();
    teamRepository.qChangeTeam(id, team);
    throwException();
    return id;
  }

  @PostgresqlTransaction
  public Long changeTeamWithQueryRuntimeException(Long id, Team team) {
    team.updateCreatedByAndUpdatedBy();
    teamRepository.qChangeTeam(id, team);
    throwRuntimeException();
    return id;
  }

  @PostgresqlTransaction
  public void deleteTeam(Long id) {
    teamRepository.deleteById(id);
  }

  @PostgresqlTransaction
  public void deleteTeamWithQuery(Long id) {
    teamRepository.qDeleteById(id);
  }

  private Team findTeamInternal(Long id) {
    return teamRepository.findById(id).orElseThrow(ServiceRuntimeException::new);
  }

  private void throwException() throws Exception {
    throw new Exception("postgresql 에러 발생");
  }

  private void throwRuntimeException() {
    throw new ServiceRuntimeException("postgresql 런타임 에러 발생");
  }

}
