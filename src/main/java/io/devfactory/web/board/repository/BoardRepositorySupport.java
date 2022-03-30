package io.devfactory.web.board.repository;

import io.devfactory.web.board.domain.mysql.Board;

import java.util.List;

public interface BoardRepositorySupport {

  List<Board> qFindAll();

  void qChangeBoard(Long id, Board changedBoard);

  void qDeleteById(Long id);

}
