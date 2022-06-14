package io.devfactory.web.board.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class SliceBoardsResponseView {

  private final int count;
  private final boolean hasNext;
  private final List<BoardResponseView> boards;

  private SliceBoardsResponseView(int count, boolean hasNext, List<BoardResponseView> boards) {
    this.count = count;
    this.hasNext = hasNext;
    this.boards = boards;
  }

  public static SliceBoardsResponseView of(Slice<BoardResponseView> slice) {
    return new SliceBoardsResponseView(slice.getNumberOfElements(), slice.hasNext(), slice.getContent());
  }

}
