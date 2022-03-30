package io.devfactory.web.team.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.devfactory.web.team.domain.postgresql.Team;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static io.devfactory.web.team.domain.postgresql.QTeam.team;


@RequiredArgsConstructor
public class TeamRepositorySupportImpl implements TeamRepositorySupport {

  private final JPAQueryFactory postgresqlJpaQueryFactory;

  @Override
  public List<Team> qFindAll() {
    return postgresqlJpaQueryFactory.selectFrom(team).fetch();
  }

  @Override
  public void qChangeTeam(Long id, Team changeTeam) {
    // @formatter:off
    postgresqlJpaQueryFactory
      .update(team)
        .set(team.title, changeTeam.getTitle())
        .set(team.description, changeTeam.getDescription())
        .set(team.teamType, changeTeam.getTeamType())
        .set(team.updatedBy, changeTeam.getUpdatedBy())
        .set(team.updatedDate, LocalDateTime.now())
        .where(team.id.eq(id))
      .execute();
    // @formatter:on
  }

  @Override
  public void qDeleteById(Long id) {
    // @formatter:off
    postgresqlJpaQueryFactory
      .delete(team)
      .where(team.id.eq(id))
      .execute();
    // @formatter:on
  }

}
