package io.devfactory.web.board.service;

import io.devfactory.global.common.annotation.MysqlTransaction;
import io.devfactory.global.common.resolver.SimplePageable;
import io.devfactory.global.error.ServiceRuntimeException;
import io.devfactory.web.board.domain.mysql.Board;
import io.devfactory.web.board.repository.BoardRepository;
import io.devfactory.web.member.domain.mysql.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"squid:S112"})
@MysqlTransaction
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

  @MysqlTransaction
  public Long createBoard(Board board, Member member) {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    return savedBoard.getId();
  }

  @MysqlTransaction
  public Long createBoardException(Board board, Member member) throws Exception {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    throwException();
    return savedBoard.getId();
  }

  @MysqlTransaction
  public Long createBoardRuntimeException(Board board, Member member) {
    board.updateMember(member);
    final var savedBoard = boardRepository.save(board);
    throwRuntimeException();
    return savedBoard.getId();
  }

  @MysqlTransaction
  public Long changeBoard(Long id, Board board, Member member) {
    final var findBoard = findBoardInternal(id);
    findBoard.changeBoard(board, member);
    return findBoard.getId();
  }

  @MysqlTransaction
  public Long changeBoardWithQuery(Long id, Board board, Member member) {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    return id;
  }

  @MysqlTransaction
  public Long changeBoardWithQueryException(Long id, Board board, Member member) throws Exception {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    throwException();
    return id;
  }

  @MysqlTransaction
  public Long changeBoardWithQueryRuntimeException(Long id, Board board, Member member) {
    board.changeBoard(board, member);
    boardRepository.qChangeBoard(id, board);
    throwRuntimeException();
    return id;
  }

  @MysqlTransaction
  public void deleteBoard(Long id) {
    boardRepository.deleteById(id);
  }

  @MysqlTransaction
  public void deleteBoardWithQuery(Long id) {
    boardRepository.qDeleteById(id);
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
