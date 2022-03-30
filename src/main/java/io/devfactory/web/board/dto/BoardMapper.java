package io.devfactory.web.board.dto;

import io.devfactory.web.board.domain.mysql.Board;
import io.devfactory.web.board.dto.request.BoardCreateRequestView;
import io.devfactory.web.board.dto.request.BoardModifyRequestView;
import io.devfactory.web.board.dto.response.BoardResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = BoardTranslatorMapper.class)
public interface BoardMapper {

  BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

  Board createViewOf(BoardCreateRequestView createRequestView);

  Board modifyViewOf(BoardModifyRequestView modifyRequestView);

  @Mapping(source = "boardType", target = "boardTypeName", qualifiedBy = BoardTypeToValue.class)
  @Mapping(source = "createdBy.id", target = "writerId")
  @Mapping(source = "createdBy.username", target = "writerName")
  BoardResponseView toView(Board board);

  List<BoardResponseView> toList(List<Board> boards);

}
