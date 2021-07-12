package dev.educosta.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * POJO created based on Notion API Response using the web site https://json2csharp.com/json-to-pojo
 * Adjusts was made after the creation.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotionQueryDatabaseResponse {

  private String object;
  private List<Result> results;
  private String next_cursor;
  private boolean has_more;


  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Parent {

    private String type;
    private String database_id;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RemoveTitle {

    private String id;
    private String type;
    private boolean checkbox;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Relation {

    private String id;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MediaTitle {

    private String id;
    private String type;
    private List<Relation> relation;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RemoveHashTags {

    private String id;
    private String type;
    private boolean checkbox;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class IntegrationLog {

    private String id;
    private String type;
    private List<Object> rich_text;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Array {

    private String type;
    private String url;
    private List<Object> text;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Rollup {

    private String type;
    private List<Array> array;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MediaURL {

    private String id;
    private String type;
    private Rollup rollup;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Formula {

    private String type;
    private String string;
    private String number;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShareText {

    private String id;
    private String type;
    private Formula formula;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SharedOn {

    private String id;
    private String type;
    private List<Object> multi_select;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Date {

    private String start;
    private String end;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShareAt {

    private String id;
    private String type;
    private Date date;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MultiSelect {

    private String id;
    private String name;
    private String color;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class HashTags {

    private String id;
    private String type;
    private List<MultiSelect> multi_select;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Description {

    private String id;
    private String type;
    private List<Object> rich_text;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Text {

    private String content;
    private Object link;
    private String type;
    private Text text;
    private Annotations annotations;
    private String plain_text;
    private Object href;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Annotations {

    private boolean bold;
    private boolean italic;
    private boolean strikethrough;
    private boolean underline;
    private boolean code;
    private String color;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MediaDescription {

    private String id;
    private String type;
    private Rollup rollup;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NoteTitle {

    private String id;
    private String type;
    private List<Object> relation;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShareMediaText {

    private String id;
    private String type;
    private Formula formula;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class RemoveURL {

    private String id;
    private String type;
    private boolean checkbox;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShareNoteText {

    private String id;
    private String type;
    private Formula formula;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Twitter280ch {

    private String id;
    private String type;
    private Formula formula;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Title {

    private String type;
    private Text text;
    private Annotations annotations;
    private String plain_text;
    private Object href;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Name {

    private String id;
    private String type;
    private List<Title> title;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Properties {

    @JsonProperty("Remove title")
    private RemoveTitle removeTitle;
    @JsonProperty("Media title")
    private MediaTitle mediaTitle;
    @JsonProperty("Remove hash tags")
    private RemoveHashTags removeHashTags;
    @JsonProperty("*Integration Log")
    private IntegrationLog integrationLog;
    @JsonProperty("Media URL")
    private MediaURL mediaURL;
    @JsonProperty("*Share text")
    private ShareText shareText;
    @JsonProperty("*Shared on")
    private SharedOn sharedOn;
    @JsonProperty("*Share at")
    private ShareAt shareAt;
    @JsonProperty("Hash tags")
    private HashTags hashTags;
    @JsonProperty("Description")
    private Description description;
    @JsonProperty("Media description")
    private MediaDescription mediaDescription;
    @JsonProperty("Note title")
    private NoteTitle noteTitle;
    @JsonProperty("Share media text")
    private ShareMediaText shareMediaText;
    @JsonProperty("Remove URL")
    private RemoveURL removeURL;
    @JsonProperty("Share note text")
    private ShareNoteText shareNoteText;
    @JsonProperty("# Twitter (280ch)")
    private Twitter280ch twitter280ch;
    @JsonProperty("Name")
    private Name name;
  }

  @Getter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Result {

    private String object;
    private String id;
    private String created_time;
    private String last_edited_time;
    private Parent parent;
    private boolean archived;
    private Properties properties;
    private String url;
  }

}
