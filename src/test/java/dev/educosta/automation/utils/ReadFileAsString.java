package dev.educosta.automation.utils;

import dev.educosta.automation.AbstractIntegrationTest;
import lombok.SneakyThrows;

public interface ReadFileAsString {

  @SneakyThrows
  default String readJson(String file, Class<?> resource) {
    return new String(resource
        .getResourceAsStream("/" + file)
        .readAllBytes());
  }

  @SneakyThrows
  default String readJson(String file) {
    return readJson(file, AbstractIntegrationTest.class);
  }


}
