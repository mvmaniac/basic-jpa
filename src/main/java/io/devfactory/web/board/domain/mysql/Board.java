package io.devfactory.web.board.domain.mysql;

import io.devfactory.global.constant.Yn;
import io.devfactory.global.model.BaseTimeEntity;
import io.devfactory.web.board.domain.BoardType;
import io.devfactory.web.member.domain.mysql.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static io.devfactory.global.constant.Yn.Y;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_board")
@Entity
public class Board extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(STRING)
  @Column(columnDefinition = "varchar")
  private BoardType boardType;

  @Column
  private String title;

  @Column
  private String contents;

  @Enumerated(STRING)
  @Column(columnDefinition = "varchar")
  private Yn useYn;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "created_by", updatable = false)
  private Member createdBy;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "updated_by")
  private Member updatedBy;

  @Builder
  private Board(BoardType boardType, String title, String contents, Member member) {
    this.boardType = boardType;
    this.title = title;
    this.contents = contents;
    this.useYn = Y;
    this.createdBy = member;
    this.updatedBy = member;
  }

  public void updateMember(Member member) {
    this.createdBy = member;
    this.updatedBy = member;
  }

  public void changeBoard(Board board, Member member) {
    this.title = board.getTitle();
    this.contents = board.getContents();
    this.useYn = board.getUseYn();
    this.updatedBy = member;
  }

}
