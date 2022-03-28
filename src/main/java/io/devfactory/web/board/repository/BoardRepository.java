package io.devfactory.web.board.repository;

import io.devfactory.web.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositorySupport {

  @Override
  @EntityGraph(attributePaths = "createdBy")
  @Query("select b from Board b")
  List<Board> findAll();

  @Override
  @EntityGraph(attributePaths = "createdBy")
  Optional<Board> findById(Long id);

  @EntityGraph(attributePaths = "createdBy")
  // 카운트 쿼리를 분리할 수 있음
  // 기본 쿼리도 명시 해야 함, 카운트 쿼리만 있으면 동작하지 않음?
  // 변경 된 카운트 쿼리인지 확인 하기 위해 title로 함
  @Query(value = "select b from Board b", countQuery = "select count(b.title) from Board b")
  Page<Board> findBoardsByPage(Pageable pageable);

  @EntityGraph(attributePaths = "createdBy")
  @Query(value = "select b from Board b")
  Slice<Board> findBoardsBySlice(Pageable pageable);

}
