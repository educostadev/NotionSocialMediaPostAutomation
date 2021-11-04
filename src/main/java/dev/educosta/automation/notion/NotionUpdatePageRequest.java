package dev.educosta.automation.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * POJO created based on Notion API Response using the web site https://json2csharp.com/json-to-pojo . Adjusts was made after the creation.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotionUpdatePageRequest {

  private Properties properties;

  public static class NotionUpdatePageRequestBuilder {

    public NotionUpdatePageRequestBuilder sharedOn(String text) {
      initializePropertiesField();
      if (properties.sharedOn == null) {
        properties.sharedOn = SharedOn.builder()
            .multi_select(new ArrayList<>())
            .build();
      }
      properties.sharedOn.multi_select.add(new MultiSelect(text));
      return this;
    }

    public NotionUpdatePageRequestBuilder sharedAt(ZonedDateTime dateTime) {
      initializePropertiesField();
      properties.sharedTime = SharedTime.builder()
          .date(new DateText(
              DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(dateTime)
          ))
          .build();
      return this;
    }

    public NotionUpdatePageRequestBuilder addLog(String text, LogColor color) {
      initializePropertiesField();
      if (properties.integrationLog == null) {
        properties.integrationLog = IntegrationLog.builder()
            .rich_text(new ArrayList<>())
            .build();
      }
      var richText = RichText.builder()
          .type("text")
          .text(new Text(text + "\n"))
          .annotations(new Annotations(color.name().toLowerCase()))
          .build();
      properties.integrationLog.rich_text.add(richText);
      return this;
    }

    private void initializePropertiesField() {
      if (properties == null) {
        properties = Properties.builder().build();
      }
    }

  }

  public enum LogColor {
    BLUE,
    RED,
    GREEN
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class MultiSelect {

    private String name;
  }


  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class SharedOn {

    private List<MultiSelect> multi_select;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class DateText {

    private String start;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class SharedTime {

    private DateText date;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Text {

    private String content;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Annotations {

    private String color;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class RichText {

    private String type;
    private Text text;
    private Annotations annotations;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class IntegrationLog {

    private List<RichText> rich_text;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Properties {

    @JsonProperty("*Shared on")
    private SharedOn sharedOn;
    @JsonProperty("*Shared time")
    private SharedTime sharedTime;
    @JsonProperty("*Integration Log")
    private IntegrationLog integrationLog;
  }

}
