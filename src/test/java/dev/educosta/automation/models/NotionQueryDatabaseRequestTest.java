package dev.educosta.automation.models;


import static dev.educosta.automation.utils.ModelFactory.createNotionQueryDatabaseRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.educosta.automation.utils.JSONSerializationUtilTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class NotionQueryDatabaseRequestTest implements JSONSerializationUtilTest {

  @SneakyThrows
  @Test
  void jsonSerializationTest() {
    assertNotNull(jsonSerialization(createNotionQueryDatabaseRequest()));
  }


}