package io.devfactory.web.board.repository;

import io.devfactory.web.board.domain.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public interface BoardRepository extends JpaRepository<Board, Long> {

  @Override
  @EntityGraph(attributePaths = "createdBy")
  @Query("select b from Board b")
  List<Board> findAll();

  @Override
  @EntityGraph(attributePaths = "createdBy")
  Optional<Board> findById(Long id);

}
