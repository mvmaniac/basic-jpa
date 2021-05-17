package io.devfactory.global.constant;

public enum Yn implements EnumModel {

  Y("Y"),
  N("N");

  private final String value;

  Yn(String value) {
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
