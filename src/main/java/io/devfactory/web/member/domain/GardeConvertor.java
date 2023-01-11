package io.devfactory.web.member.domain;

import io.devfactory.global.util.EnumUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GardeConvertor implements AttributeConverter<Grade, String> {

  @Override
  public String convertToDatabaseColumn(Grade grade) {
    return grade.getKey();
  }

  @Override
  public Grade convertToEntityAttribute(String dbData) {
    return EnumUtils.keyOfEnumModel(Grade.values(), dbData).orElse(null);
  }

}
