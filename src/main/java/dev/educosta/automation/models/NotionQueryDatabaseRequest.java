package dev.educosta.automation.models;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

}

