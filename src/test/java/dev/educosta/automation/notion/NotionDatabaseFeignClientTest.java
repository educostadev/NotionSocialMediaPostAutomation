package dev.educosta.automation.notion;

import static dev.educosta.automation.utils.ModelFactory.createNotionQueryDatabaseRequest;
import static dev.educosta.automation.utils.ModelFactory.createNotionUpdatePageRequest;
import static java.text.MessageFormat.format;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dev.educosta.automation.AbstractIntegrationTest;
import dev.educosta.automation.TokenPropertiesEnum;
import dev.educosta.automation.utils.ReadFileAsString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
/**
 * This test can fire request direct on notion when the properties -Dxxxx are passed as jvm argument with values from your account.
 * (see @BeforeAll above)
 * -DnotionBearerToken=<value>
 * -DnotionShareDatabaseId=<value>
 * -DnotionApiUrl=https://api.notion.com
 */
class NotionDatabaseFeignClientTest extends AbstractIntegrationTest
    implements ReadFileAsString {

  private NotionDatabaseFeignClient feignClient;

  private static final String FAKE_TOKEN = "notion_bearer_token_value";

  @BeforeAll
  static void init() {
    TokenPropertiesEnum.NOTION_TOKEN.setValueIfPropertyEmpty(FAKE_TOKEN);
    TokenPropertiesEnum.NOTION_SHARE_DATABASE_ID.setValueIfPropertyEmpty("notion_share_database_id_value");
    TokenPropertiesEnum.NOTION_API_URL.setValueIfPropertyEmpty("http://localhost:8080");
  }

  @AfterAll
  static void end() {
    TokenPropertiesEnum.clearAllProperties();
  }

  @BeforeEach
  public void setup() {
    feignClient = NotionService.builder().build().getNotionDatabaseFeignClient();
  }

  @Test
  void whenQueryDatabaseThenReturnSuccess() {
    stubPostFor(
        format("/v1/databases/{0}/query", TokenPropertiesEnum.NOTION_SHARE_DATABASE_ID.value()),
        readJson("notionQueryDatabaseResponse.json")
    );
    var response = feignClient.query(
        TokenPropertiesEnum.NOTION_TOKEN.value(),
        TokenPropertiesEnum.NOTION_SHARE_DATABASE_ID.value(),
        createNotionQueryDatabaseRequest()
    );
    assertThat(response, notNullValue());
    log.info("{}", response);
  }

  @Test
  void updatePageWithSuccess() {

    String pageId = "f2a7045ce3254589b4ea3b636cc0c10c";
    var body = createNotionUpdatePageRequest();
    if (shouldUseMock()) {
      stubPatchFor(
          format("/v1/pages/{0}", pageId),
          readJson("notionUpdatePageResponse.json")
      );
    }

    assertDoesNotThrow(() ->
        feignClient.updatePage(
            TokenPropertiesEnum.NOTION_TOKEN.value(),
            pageId,
            body
        )
    );

  }


  /**
   * When a fake token is being used we will mock mock the external call.
   */
  private boolean shouldUseMock() {
    return TokenPropertiesEnum.NOTION_TOKEN.value().equals(FAKE_TOKEN);
  }


}