package io.devfactory.web.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.devfactory.web.board.domain.mysql.Board;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static io.devfactory.web.board.domain.mysql.QBoard.board;
import static io.devfactory.web.member.domain.mysql.QMember.member;

@RequiredArgsConstructor
public class BoardRepositorySupportImpl implements BoardRepositorySupport {

  private final JPAQueryFactory mysqlJpaQueryFactory;

  @Override
  public List<Board> qFindAll() {
    // @formatter:off
    return mysqlJpaQueryFactory
        .selectFrom(board)
        .innerJoin(board.createdBy, member)
          .fetchJoin()
        .fetch();
    // @formatter:on
  }

  @Override
  public void qChangeBoard(Long id, Board changedBoard) {
    // @formatter:off
    mysqlJpaQueryFactory
      .update(board)
        .set(board.title, changedBoard.getTitle())
        .set(board.contents, changedBoard.getContents())
        .set(board.updatedBy, changedBoard.getUpdatedBy())
        .set(board.updatedDate, LocalDateTime.now())
      .where(board.id.eq(id))
      .execute();
    // @formatter:on
  }

  @Override
  public void qDeleteById(Long id) {
    // @formatter:off
    mysqlJpaQueryFactory
      .delete(board)
      .where(board.id.eq(id))
      .execute();
    // @formatter:on
  }

}
