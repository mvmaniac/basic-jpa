package io.devfactory.web.board.api;

import io.devfactory.global.common.resolver.SimplePageable;
import io.devfactory.web.board.dto.BoardMapper;
import io.devfactory.web.board.dto.request.BoardCreateRequestView;
import io.devfactory.web.board.dto.request.BoardModifyRequestView;
import io.devfactory.web.board.dto.response.BoardResponseView;
import io.devfactory.web.board.dto.response.PageBoardsResponseView;
import io.devfactory.web.board.service.BoardService;
import io.devfactory.web.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static io.devfactory.global.util.UriUtils.buildUriToRetrieveById;

@RequestMapping("/api/boards")
@RestController
public class BoardApi {

  private final BoardService boardService;

  public BoardApi(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping
  public ResponseEntity<List<BoardResponseView>> retrieveBoards() {
    final var findBoards = boardService.findBoards();
    return ResponseEntity.ok(BoardMapper.INSTANCE.toList(findBoards));
  }

  @GetMapping("/page")
  public ResponseEntity<PageBoardsResponseView> retrieveBoardsByPage(SimplePageable pageable) {
    final var findPageBoards = boardService.findBoards(pageable);
    final var pageBoards = findPageBoards.map(BoardMapper.INSTANCE::toView);
    return ResponseEntity.ok(new PageBoardsResponseView(pageBoards.getTotalElements(), pageBoards.getTotalPages(), pageBoards.getContent()));
  }

  @GetMapping("/slice")
  public ResponseEntity<List<BoardResponseView>> retrieveBoardsBySlice() {
    final var findBoards = boardService.findBoardsBySlice();
    return ResponseEntity.ok(BoardMapper.INSTANCE.toList(findBoards));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoardResponseView> retrieveBoard(@PathVariable("id") Long id) {
    final var findBoard = boardService.findBoard(id);
    return ResponseEntity.ok(BoardMapper.INSTANCE.toView(findBoard));
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> createBoard(
      @Valid @RequestBody BoardCreateRequestView requestView) {
    final var savedId = boardService.createBoard(requestView.toBoard(), getTempMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> modifyBoard(@PathVariable("id") Long id,
      @Valid @RequestBody BoardModifyRequestView requestView) {
    final var changedId = boardService.changeBoard(id, requestView.toBoard(), getTempMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeBoard(@PathVariable("id") Long id) {
    boardService.deleteBoard(id);
    return ResponseEntity.noContent().build();
  }

  // TODO: 임시 회원 만들기, 로그인 처리가 된다면 지워야 함
  private Member getTempMember() {
    return Member.of(1L);
  }

}
