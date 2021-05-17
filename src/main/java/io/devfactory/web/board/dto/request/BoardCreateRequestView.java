package io.devfactory.web.board.dto.request;

import io.devfactory.web.board.domain.Board;
import io.devfactory.web.board.domain.BoardType;
import io.devfactory.web.board.dto.BoardMapper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateRequestView {

  private final BoardType boardType;
  private final String title;
  private final String contents;

  @Builder
  private BoardCreateRequestView(BoardType boardType, String title, String contents) {
    this.boardType = boardType;
    this.title = title;
    this.contents = contents;
  }

  public Board toBoard() {
    return BoardMapper.INSTANCE.createViewOf(this);
  }

}
