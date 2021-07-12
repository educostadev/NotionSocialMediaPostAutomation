package dev.educosta.automation.clients;

import dev.educosta.automation.models.NotionQueryDatabaseRequest;
import dev.educosta.automation.models.NotionQueryDatabaseResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface NotionDatabaseClient {

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

}
