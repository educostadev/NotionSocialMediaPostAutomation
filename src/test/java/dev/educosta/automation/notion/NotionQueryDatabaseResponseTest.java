package dev.educosta.automation.notion;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.educosta.automation.utils.JSONSerializationUtilTest;
import dev.educosta.automation.utils.ReadFileAsString;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class NotionQueryDatabaseResponseTest
    implements JSONSerializationUtilTest, ReadFileAsString {

  ObjectMapper mapper =
      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


  @SneakyThrows
  @Test
  void jsonSerializationTest() {
    assertNotNull(jsonSerialization(createObject()));
  }

  @SneakyThrows
  @Test
  void jsonToObject() {
    String json = readJson("notionQueryDatabaseResponse.json");
    var response = mapper.readValue(json, NotionQueryDatabaseResponse.class);
    assertNotNull(response);
  }

  NotionQueryDatabaseResponse createObject() {
    return NotionQueryDatabaseResponse.builder().build();
  }

}