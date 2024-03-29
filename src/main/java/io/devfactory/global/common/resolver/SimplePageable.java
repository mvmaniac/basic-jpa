package io.devfactory.global.common.resolver;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class SimplePageable {

  private final int page;
  private final int size;

  public SimplePageable() {
    this(0);
  }

  public SimplePageable(int page) {
    this(page, 5);
  }

  public SimplePageable(int page, int size) {
    if (page < 0) {
      throw new IllegalArgumentException("Page must be greater or equals zero.");
    }

    if (size < 1) {
      throw new IllegalArgumentException("Size must be greater than zero.");
    }
    
    this.page = page;
    this.size = size;
  }

  public PageRequest toPageRequest(Sort sort) {
    return PageRequest.of(page, size, sort);
  }

}
