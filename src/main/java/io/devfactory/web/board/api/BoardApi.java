package io.devfactory.web.board.api;

import io.devfactory.global.common.resolver.SimplePageable;
import io.devfactory.web.board.dto.BoardMapper;
import io.devfactory.web.board.dto.request.BoardCreateRequestView;
import io.devfactory.web.board.dto.request.BoardModifyRequestView;
import io.devfactory.web.board.dto.response.BoardResponseView;
import io.devfactory.web.board.dto.response.PageBoardsResponseView;
import io.devfactory.web.board.dto.response.SliceBoardsResponseView;
import io.devfactory.web.board.service.BoardService;
import io.devfactory.web.member.domain.mysql.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static io.devfactory.global.util.UriUtils.buildUriToRetrieveById;

@SuppressWarnings("squid:S112")
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

  @GetMapping("/query")
  public ResponseEntity<List<BoardResponseView>> retrieveBoardsWithQuery() {
    final var foundBoards = boardService.findBoardsWitQuery();
    return ResponseEntity.ok(BoardMapper.INSTANCE.toList(foundBoards));
  }

  @GetMapping("/page")
  public ResponseEntity<PageBoardsResponseView> retrieveBoardsByPage(SimplePageable pageable) {
    final var foundBoards = boardService.findBoardsByPage(pageable);
    final var convertedBoards = foundBoards.map(BoardMapper.INSTANCE::toView);
    return ResponseEntity.ok(PageBoardsResponseView.of(convertedBoards));
  }

  @GetMapping("/slice")
  public ResponseEntity<SliceBoardsResponseView> retrieveBoardsBySlice(SimplePageable pageable) {
    final var foundBoards = boardService.findBoardsBySlice(pageable);
    final var convertedBoards = foundBoards.map(BoardMapper.INSTANCE::toView);
    return ResponseEntity.ok(SliceBoardsResponseView.of(convertedBoards));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoardResponseView> retrieveBoard(@PathVariable("id") Long id) {
    final var foundBoard = boardService.findBoard(id);
    return ResponseEntity.ok(BoardMapper.INSTANCE.toView(foundBoard));
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> createBoard(
      @Valid @RequestBody BoardCreateRequestView requestView) {
    final var savedId = boardService.createBoard(requestView.toBoard(), getTempMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/ex")
  public ResponseEntity<Map<String, Object>> createBoardException(
      @Valid @RequestBody BoardCreateRequestView requestView) throws Exception {
    final var savedId = boardService.createBoardException(requestView.toBoard(), getTempMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/run-ex")
  public ResponseEntity<Map<String, Object>> createBoardRuntimeException(
      @Valid @RequestBody BoardCreateRequestView requestView) {
    final var savedId = boardService.createBoardRuntimeException(requestView.toBoard(), getTempMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> modifyBoard(@PathVariable("id") Long id,
      @Valid @RequestBody BoardModifyRequestView requestView) {
    final var changedId = boardService.changeBoard(id, requestView.toBoard(), getTempMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQuery(@PathVariable("id") Long id,
      @Valid @RequestBody BoardModifyRequestView requestView) {
    final var changedId = boardService.changeBoardWithQuery(id, requestView.toBoard(), getTempMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/ex")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQueryException(
      @PathVariable("id") Long id,
      @Valid @RequestBody BoardModifyRequestView requestView) throws Exception {
    final var changedId = boardService.changeBoardWithQueryException(id, requestView.toBoard(), getTempMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/run-ex")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQueryRuntimeException(
      @PathVariable("id") Long id, @Valid @RequestBody BoardModifyRequestView requestView) {
    final var changedId = boardService.changeBoardWithQueryRuntimeException(id, requestView.toBoard(), getTempMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeBoard(@PathVariable("id") Long id) {
    boardService.deleteBoard(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/query/{id}")
  public ResponseEntity<Object> removeBoardWithQuery(@PathVariable("id") Long id) {
    boardService.deleteBoardWithQuery(id);
    return ResponseEntity.noContent().build();
  }

  // TODO: 임시 회원 만들기, 로그인 처리가 된다면 지워야 함
  private Member getTempMember() {
    return Member.of(1L);
  }

}
