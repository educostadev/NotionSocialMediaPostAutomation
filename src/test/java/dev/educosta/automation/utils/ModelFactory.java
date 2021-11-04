package dev.educosta.automation.utils;

import dev.educosta.automation.notion.NotionQueryDatabaseRequest;
import dev.educosta.automation.notion.NotionUpdatePageRequest;
import dev.educosta.automation.notion.NotionUpdatePageRequest.LogColor;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class ModelFactory {

  public static NotionQueryDatabaseRequest createNotionQueryDatabaseRequest() {
    return NotionQueryDatabaseRequest.createRequestForQuerySharedAt(
        LocalDate.of(2021, 7, 6)
    );
  }

  public static NotionUpdatePageRequest createNotionUpdatePageRequest() {
    var updatePageRequest = NotionUpdatePageRequest.builder();
    updatePageRequest.sharedOn("twitter");
    updatePageRequest.sharedOn("linkedin");
    updatePageRequest.sharedAt(ZonedDateTime.now());
    updatePageRequest.addLog("Line1", LogColor.BLUE);
    updatePageRequest.addLog("Line2", LogColor.RED);
    updatePageRequest.addLog("Line3", LogColor.GREEN);
    return updatePageRequest.build();
  }
}
