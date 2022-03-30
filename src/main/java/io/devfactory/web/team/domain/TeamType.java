package io.devfactory.web.team.domain;

import io.devfactory.global.constant.EnumModel;

public enum TeamType implements EnumModel {

  GENERAL( "일반"),
  STUDY( "공부"),
  SPORTS( "운동");

  private final String value;

  TeamType(String value) {
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
