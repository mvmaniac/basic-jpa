package io.devfactory.web.member.dto;

import io.devfactory.global.util.EnumUtils;
import io.devfactory.web.member.domain.Grade;
import org.mapstruct.Mapper;

@Mapper
public interface MemberTranslatorMapper {

  default Grade keyOf(String key) {
    return EnumUtils.keyOfEnumModel(Grade.values(), key).orElse(null);
  }

  default String gradeOf(Grade grade) {
    return grade.getKey();
  }

}
