package io.devfactory.web.board.dto.request;

import io.devfactory.web.board.domain.Board;
import io.devfactory.web.board.dto.BoardMapper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardModifyRequestView {

  private final String title;
  private final String contents;

  @Builder
  private BoardModifyRequestView(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public Board toBoard() {
    return BoardMapper.INSTANCE.modifyViewOf(this);
  }

}
