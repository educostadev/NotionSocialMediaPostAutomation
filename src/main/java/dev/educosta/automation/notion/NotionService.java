package dev.educosta.automation.notion;

import static dev.educosta.automation.FeignClientBuilder.createClient;

import dev.educosta.automation.TokenPropertiesEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class NotionService {

  private final NotionDatabaseFeignClient notionDatabaseFeignClient = createClient(
      NotionDatabaseFeignClient.class,
      TokenPropertiesEnum.NOTION_API_URL.value()
  );

  public NotionQueryDatabaseResponse query(NotionQueryDatabaseRequest body) {
    return notionDatabaseFeignClient.query(
        TokenPropertiesEnum.NOTION_TOKEN.value(),
        TokenPropertiesEnum.NOTION_SHARE_DATABASE_ID.value(),
        body
    );
  }

  public void updatePage(String notionPageId, NotionUpdatePageRequest body) {
    notionDatabaseFeignClient.updatePage(
        TokenPropertiesEnum.NOTION_TOKEN.value(),
        notionPageId,
        body
    );
  }

}
