package io.devfactory.web.board.service;

import io.devfactory.global.common.resolver.SimplePageable;
import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.board.domain.Board;
import io.devfactory.web.board.repository.BoardRepository;
import io.devfactory.web.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public List<Board> findBoards() {
    return boardRepository.findAll();
  }

  public Page<Board> findBoardsByPage(SimplePageable pageable) {
    final var pageRequest = pageable.toPageRequest(Sort.by(Sort.Direction.DESC, "createdDate"));
    return boardRepository.findBoardsByPage(pageRequest);
  }

  public Slice<Board> findBoardsBySlice(SimplePageable pageable) {
    final var pageRequest = pageable.toPageRequest(Sort.by(Sort.Direction.DESC, "createdDate"));
    return boardRepository.findBoardsBySlice(pageRequest);
  }

  public Board findBoard(Long id) {
    return findBoardInternal(id);
  }

  @Transactional
  public Long createBoard(Board board, Member member) {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    return savedBoard.getId();
  }

  @Transactional
  public Long changeBoard(Long id, Board board, Member member) {
    final var findBoard = findBoardInternal(id);
    findBoard.changeBoard(board, member);
    return findBoard.getId();
  }

  @Transactional
  public void deleteBoard(Long id) {
    boardRepository.deleteById(id);
  }

  private Board findBoardInternal(Long id) {
    return boardRepository.findById(id).orElseThrow(ServiceRuntimeException::new);
  }

}
