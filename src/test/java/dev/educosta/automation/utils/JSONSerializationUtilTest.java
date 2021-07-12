package dev.educosta.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public interface JSONSerializationUtilTest {

  @SneakyThrows
  default Object jsonSerialization(Object value) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    String json = objectMapper.writeValueAsString(value);
    return objectMapper.readValue(json, value.getClass());
  }
}
