package dev.educosta.automation.notion;

import static dev.educosta.automation.utils.ModelFactory.createNotionUpdatePageRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.educosta.automation.utils.JSONSerializationUtilTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class NotionUpdatePageRequestTest implements JSONSerializationUtilTest {

  @SneakyThrows
  @Test
  void jsonSerializationTest() {
    assertNotNull(jsonSerialization(createNotionUpdatePageRequest()));
  }

}