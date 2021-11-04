package dev.educosta.automation.notion;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface NotionDatabaseFeignClient {

  @RequestLine("POST /v1/databases/{notionDatabaseId}/query")
  @Headers({
      "Content-Type: application/json",
      "Notion-Version: 2021-05-13",
      "Authorization: Bearer {notionBearerToken}"
  })
  NotionQueryDatabaseResponse query(
      @Param("notionBearerToken") String notionBearerToken,
      @Param("notionDatabaseId") String notionDatabaseId,
      NotionQueryDatabaseRequest body
  );

  @RequestLine("PATCH /v1/pages/{notionPageId}")
  @Headers({
      "Content-Type: application/json",
      "Notion-Version: 2021-05-13",
      "Authorization: Bearer {notionBearerToken}"
  })
  void updatePage(
      @Param("notionBearerToken") String notionBearerToken,
      @Param("notionPageId") String notionPageId,
      NotionUpdatePageRequest body
  );

}
