package dev.educosta.automation.utils;

import dev.educosta.automation.models.NotionQueryDatabaseRequest;
import dev.educosta.automation.models.NotionQueryDatabaseRequest.And;
import dev.educosta.automation.models.NotionQueryDatabaseRequest.Date;
import dev.educosta.automation.models.NotionQueryDatabaseRequest.Filter;
import dev.educosta.automation.models.NotionQueryDatabaseRequest.Sort;
import java.util.List;

public class ModelFactory {

  public static NotionQueryDatabaseRequest createNotionQueryDatabaseRequest() {
    return NotionQueryDatabaseRequest.builder()
        .filter(Filter.builder()
            .and(List.of(
                And.builder()
                    .property("*Share at")
                    .date(Date.builder().equals("2021-07-06").build())
                    .build(),
                And.builder()
                    .property("*Shared time")
                    .date(Date.builder().is_empty(true).build())
                    .build()
            )).build())
        .sorts(List.of(
            Sort.builder()
                .property("*Share at")
                .direction("ascending")
                .build()
        ))
        .build();
  }
}
