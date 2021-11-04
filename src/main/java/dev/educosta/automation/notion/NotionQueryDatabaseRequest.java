package dev.educosta.automation.notion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * POJO created based on Notion API Response using the web site https://json2csharp.com/json-to-pojo Adjusts was made after the creation.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotionQueryDatabaseRequest {

  private Filter filter;
  private List<Sort> sorts;

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Date {

    private String equals;
    private Boolean is_empty;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class And {

    private String property;
    private Date date;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Filter {

    private List<And> and;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Sort {

    private String property;
    private String direction;
  }

  public static NotionQueryDatabaseRequest createRequestForQuerySharedAt(LocalDate localDate) {
    return NotionQueryDatabaseRequest.builder()
        .filter(Filter.builder()
            .and(List.of(
                And.builder()
                    .property("*Share at")
                    .date(Date.builder().equals(
                        DateTimeFormatter.ISO_LOCAL_DATE.format(localDate)
                    ).build())
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

