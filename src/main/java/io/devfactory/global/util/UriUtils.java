package io.devfactory.global.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class UriUtils {

  public static URI buildUriToRetrieveById(Long retrieveId) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(retrieveId)
        .toUri()
        ;
  }

}
