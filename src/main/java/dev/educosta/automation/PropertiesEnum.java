package dev.educosta.automation;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;

public enum PropertiesEnum {

  NOTION_TOKEN("notionBearerToken"),
  NOTION_SHARE_DATABASE_ID("notionShareDatabaseId"),
  NOTION_API_URL("notionApiUrl");

  @Getter
  private final String propertyName;

  PropertiesEnum(String propertyName) {
    this.propertyName = propertyName;
  }

  public String value() {
    return Optional.ofNullable(System.getProperty(propertyName))
        .orElseThrow(() -> new NullPointerException(
            format(
                "JVM property {0} not specified. Add the parameter -D{0}=VALUE on the command line.",
                propertyName))
        );
  }

  public void setValue(String value) {
    System.setProperty(propertyName, requireNonNull(value));
  }


  public void setValueIfPropertyEmpty(String value) {
    Optional.ofNullable(System.getProperty(propertyName)).ifPresentOrElse(
        doNothing -> {
        }
        , () -> setValue(value)
    );
  }

  public static void clearAllProperties(){
    Stream.of(values()).forEach(value->System.clearProperty(value.propertyName));
  }

}
