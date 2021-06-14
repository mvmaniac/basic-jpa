package io.devfactory.web.board.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PageBoardsResponseView {

  private final long totalCount;
  private final int totalPage;
  private final List<BoardResponseView> boards;

  public PageBoardsResponseView(long totalCount, int totalPage, List<BoardResponseView> boards) {
    this.totalCount = totalCount;
    this.totalPage = totalPage;
    this.boards = boards;
  }

}
