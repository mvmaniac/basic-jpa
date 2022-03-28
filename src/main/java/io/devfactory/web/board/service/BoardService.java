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

@SuppressWarnings({"ClassCanBeRecord", "squid:S112"})
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

  public List<Board> findBoardsWitQuery() {
    return boardRepository.qFindAll();
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
  public Long createBoardException(Board board, Member member) throws Exception {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    throwException();
    return savedBoard.getId();
  }

  @Transactional
  public Long createBoardRuntimeException(Board board, Member member) {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    throwRuntimeException();
    return savedBoard.getId();
  }

  @Transactional
  public Long changeBoard(Long id, Board board, Member member) {
    final var findBoard = findBoardInternal(id);
    findBoard.changeBoard(board, member);
    return findBoard.getId();
  }

  @Transactional
  public Long changeBoardWithQuery(Long id, Board board, Member member) {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    return id;
  }

  @Transactional
  public Long changeBoardWithQueryException(Long id, Board board, Member member) throws Exception {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    throwException();
    return id;
  }

  @Transactional
  public Long changeBoardWithQueryRuntimeException(Long id, Board board, Member member) {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    throwRuntimeException();
    return id;
  }

  @Transactional
  public void deleteBoard(Long id) {
    boardRepository.deleteById(id);
  }

  @Transactional
  public void deleteBoardWithQuery(Long id) {
    boardRepository.deleteById(id);
  }

  private Board findBoardInternal(Long id) {
    return boardRepository.findById(id).orElseThrow(ServiceRuntimeException::new);
  }

  private void throwException() throws Exception {
    throw new Exception("postgresql 에러 발생");
  }

  private void throwRuntimeException() {
    throw new ServiceRuntimeException("postgresql 런타임 에러 발생");
  }

}
