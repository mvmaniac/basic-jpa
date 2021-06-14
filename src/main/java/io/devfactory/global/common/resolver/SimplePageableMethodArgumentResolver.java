package io.devfactory.global.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Nonnull;

import static org.springframework.util.StringUtils.hasText;

public class SimplePageableMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final String DEFAULT_PAGE_PARAMETER = "page";
  private static final String DEFAULT_SIZE_PARAMETER = "size";
  private static final int DEFAULT_MAX_LIMIT_SIZE = 5;

  private SimplePageable fallbackPageable;

  private final String pageParameterName;
  private final String sizeParameterName;

  public SimplePageableMethodArgumentResolver() {
    this(DEFAULT_PAGE_PARAMETER, DEFAULT_SIZE_PARAMETER);
  }

  public SimplePageableMethodArgumentResolver(String pageParameterName, String sizeParameterName) {
    this.pageParameterName = pageParameterName;
    this.sizeParameterName = sizeParameterName;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return SimplePageable.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(@Nonnull MethodParameter parameter,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {

    String pageParameter = webRequest.getParameter(pageParameterName);
    String sizeParameter = webRequest.getParameter(sizeParameterName);

    final var pageAndSizeGiven = hasText(pageParameter) && hasText(sizeParameter);

    if (!pageAndSizeGiven && fallbackPageable == null) {
      return null;
    }

    var page = hasText(pageParameter) ? parseAndApplyBoundaries(pageParameter, Integer.MAX_VALUE) : fallbackPageable.getPage();
    var size = hasText(sizeParameter) ? parseAndApplyBoundaries(sizeParameter, DEFAULT_MAX_LIMIT_SIZE) : fallbackPageable.getSize();

    page = Math.max(page - 1, 0);

    size = size < 1 ? fallbackPageable.getSize() : size;
    size = Math.min(size, DEFAULT_MAX_LIMIT_SIZE);

    return new SimplePageable(page, size);
  }

  private int parseAndApplyBoundaries(String parameter, int upper) {
    final var parsed = parseNumber(parameter);
    return parsed < 0 ? 0 : Math.min(parsed, upper);
  }

  public void setFallbackPageable(SimplePageable fallbackPageable) {
    this.fallbackPageable = fallbackPageable;
  }

  private int parseNumber(String text) {
    try {
      return Integer.parseInt(text);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

}
