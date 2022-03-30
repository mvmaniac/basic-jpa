package io.devfactory.web.board.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
@Getter
public class PageBoardsResponseView {

  private final long totalCount;
  private final int totalPage;
  private final boolean hasNext;
  private final List<BoardResponseView> boards;

  private PageBoardsResponseView(long totalCount, int totalPage, boolean hasNext,
      List<BoardResponseView> boards) {
    this.totalCount = totalCount;
    this.totalPage = totalPage;
    this.hasNext = hasNext;
    this.boards = boards;
  }

  public static PageBoardsResponseView of(Page<BoardResponseView> page) {
    return new PageBoardsResponseView(page.getTotalElements(), page.getTotalPages(), page.hasNext(), page.getContent());
  }

}
