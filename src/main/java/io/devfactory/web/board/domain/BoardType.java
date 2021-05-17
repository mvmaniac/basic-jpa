package io.devfactory.web.board.domain;

import io.devfactory.global.constant.EnumModel;

public enum BoardType implements EnumModel {

  NOTICE("공지사항"),
  FREE("자유게시판"),
  QUESTION("질문게시판"),
  FAQ("FAQ");

  private final String value;

  BoardType(String value) {
    this.value = value;
  }

  @Override
  public String getKey() {
    return name();
  }

  @Override
  public String getValue() {
    return value;
  }

}
