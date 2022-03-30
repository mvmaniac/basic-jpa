package io.devfactory.web.team.repository;

import io.devfactory.global.common.annotation.PostgresqlRepository;
import io.devfactory.web.team.domain.postgresql.Team;
import org.springframework.data.jpa.repository.JpaRepository;

@PostgresqlRepository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositorySupport {

}
