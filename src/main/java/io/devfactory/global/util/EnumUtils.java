package io.devfactory.global.util;

import io.devfactory.global.constant.EnumModel;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@UtilityClass
public class EnumUtils {

  public static <T extends Enum<?>> Optional<T> valueOfEnum(T[] arrays, String value) {
    return Arrays.stream(arrays)
        .filter(model -> model.name().equalsIgnoreCase(value))
        .findFirst()
        ;
  }

  public static <T extends Enum<?>> List<T> listOfEnum(T[] arrays, List<String> values) {
    return values.stream()
        .map(value -> valueOfEnum(arrays, value).orElse(null))
        .filter(Objects::nonNull)
        .toList()
        ;
  }

  public static <T extends EnumModel> Optional<T> keyOfEnumModel(T[] arrays, String key) {
    return keyOrValueOfEnumModel(arrays, model -> model.getKey().equalsIgnoreCase(key));
  }

  public static <T extends EnumModel> Optional<T> valueOfEnumModel(T[] arrays, String value) {
    return keyOrValueOfEnumModel(arrays, model -> model.getValue().equalsIgnoreCase(value));
  }

  private static <T extends EnumModel> Optional<T> keyOrValueOfEnumModel(T[] arrays,
      Predicate<EnumModel> predicate) {
    return Arrays.stream(arrays)
        .filter(predicate)
        .findFirst()
        ;
  }

  public static <T extends EnumModel> List<T> keysOfEnumModel(T[] arrays, List<String> keys) {
    return listOfEnumModel(keys, value -> keyOfEnumModel(arrays, value).orElse(null));
  }

  public static <T extends EnumModel> List<T> valuesOfEnumModel(T[] arrays,
      List<String> values) {
    return listOfEnumModel(values, value -> valueOfEnumModel(arrays, value).orElse(null));
  }

  private static <T extends EnumModel> List<T> listOfEnumModel(List<String> values,
      Function<String, T> mapper) {
    return values.stream()
        .map(mapper)
        .filter(Objects::nonNull)
        .toList()
        ;
  }

}
