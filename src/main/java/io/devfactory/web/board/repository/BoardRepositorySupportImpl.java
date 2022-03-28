package io.devfactory.web.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.devfactory.web.board.domain.Board;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static io.devfactory.web.board.domain.QBoard.board;
import static io.devfactory.web.member.domain.QMember.member;


@RequiredArgsConstructor
public class BoardRepositorySupportImpl implements BoardRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Board> qFindAll() {
    // @formatter:off
    return jpaQueryFactory
        .selectFrom(board)
        .innerJoin(board.createdBy, member)
          .fetchJoin()
        .fetch();
    // @formatter:on
  }

  @Override
  public void qChangeBoard(Long id, Board changedBoard) {
    // @formatter:off
    jpaQueryFactory
      .update(board)
        .set(board.title, changedBoard.getTitle())
        .set(board.contents, changedBoard.getContents())
        .set(board.updatedBy, changedBoard.getCreatedBy())
        .set(board.updatedDate, LocalDateTime.now())
      .where(board.id.eq(id))
      .execute();
    // @formatter:on
  }

  @Override
  public void qDeleteById(Long id) {
    // @formatter:off
    jpaQueryFactory
      .delete(board)
      .where(board.id.eq(id))
      .execute();
    // @formatter:on
  }

}
