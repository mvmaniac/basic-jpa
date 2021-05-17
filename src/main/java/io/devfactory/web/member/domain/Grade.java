package io.devfactory.web.member.domain;

import io.devfactory.global.constant.EnumModel;

public enum Grade implements EnumModel {

  NORMAL("N", "일반"),
  SILVER("S", "실버"),
  GOLD("G", "골드"),
  PLATINUM("P", "플래티넘"),
  MANIA("M", "매니아");

  private final String key;
  private final String value;

  Grade(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String getValue() {
    return value;
  }

}
