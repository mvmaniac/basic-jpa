package io.devfactory.web.board.dto;

import io.devfactory.web.board.domain.BoardType;
import org.mapstruct.Mapper;

@Mapper
public interface BoardTranslatorMapper {

  @BoardTypeToValue
  static String ofValue(BoardType boardType) {
    return boardType.getValue();
  }

}
