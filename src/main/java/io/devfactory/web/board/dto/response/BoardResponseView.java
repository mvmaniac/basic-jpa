package io.devfactory.web.board.dto.response;

import io.devfactory.global.constant.Yn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseView {

  private final Long id;
  private final String boardType;
  private final String boardTypeName;
  private final String title;
  private final String contents;
  private final Yn useYn;
  private final Long writerId;
  private final String writerName;
  private final LocalDateTime createdDate;

  @Builder
  private BoardResponseView(Long id, String boardType, String boardTypeName, String title,
      String contents, Yn useYn, Long writerId, String writerName, LocalDateTime createdDate) {
    this.id = id;
    this.boardType = boardType;
    this.boardTypeName = boardTypeName;
    this.title = title;
    this.contents = contents;
    this.useYn = useYn;
    this.writerId = writerId;
    this.writerName = writerName;
    this.createdDate = createdDate;
  }

}
