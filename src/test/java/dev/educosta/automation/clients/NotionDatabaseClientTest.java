package dev.educosta.automation.clients;

import static dev.educosta.automation.utils.ModelFactory.createNotionQueryDatabaseRequest;
import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import dev.educosta.automation.AbstractIntegrationTest;
import dev.educosta.automation.PropertiesEnum;
import dev.educosta.automation.utils.ReadFileAsString;
import dev.educosta.automation.controllers.NotionController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
/**
 * This test can fire request direct on notion when the properties -Dxxxx (see @BeforeAll above) are passed as jvm argument
 * with values from you account.
 */
class NotionDatabaseClientTest extends AbstractIntegrationTest
    implements ReadFileAsString {

  private NotionDatabaseClient feignClient;

  @BeforeAll
  static void init() {
    PropertiesEnum.NOTION_TOKEN.setValueIfPropertyEmpty("notion_bearer_token_value");
    PropertiesEnum.NOTION_SHARE_DATABASE_ID.setValueIfPropertyEmpty("notion_share_database_id_value");
    PropertiesEnum.NOTION_API_URL.setValueIfPropertyEmpty("http://localhost:8080");
  }

  @BeforeEach
  public void setup() {
    feignClient = NotionController.builder().build().getNotionDatabaseClient();
  }

  @Test
  void whenQueryDatabaseThenReturnSuccess() {
    stubPostFor(
        format("/v1/databases/{0}/query", PropertiesEnum.NOTION_SHARE_DATABASE_ID.value()),
        readJson("notionQueryDatabaseResponse.json")
    );
    var response = feignClient.query(
        PropertiesEnum.NOTION_TOKEN.value(),
        PropertiesEnum.NOTION_SHARE_DATABASE_ID.value(),
        createNotionQueryDatabaseRequest()
    );
    assertThat(response, notNullValue());
    log.info("{}", response);
  }


}