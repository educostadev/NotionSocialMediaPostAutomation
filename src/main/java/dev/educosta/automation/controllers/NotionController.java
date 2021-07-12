package dev.educosta.automation.controllers;

import static dev.educosta.automation.controllers.FeignClientBuilder.createClient;

import dev.educosta.automation.PropertiesEnum;
import dev.educosta.automation.clients.NotionDatabaseClient;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotionController {

  private final NotionDatabaseClient notionDatabaseClient = createClient(
      NotionDatabaseClient.class,
      PropertiesEnum.NOTION_API_URL.value()
  );

}
