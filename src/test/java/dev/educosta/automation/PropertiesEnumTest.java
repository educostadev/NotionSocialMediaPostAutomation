package dev.educosta.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertiesEnumTest {

  @BeforeEach
  void init() {
    PropertiesEnum.clearAllProperties();
  }

  @Test
  void failWhenPropertyNotSpecified() {
    assertThrows(
        RuntimeException.class,
        PropertiesEnum.NOTION_API_URL::value
    );
  }

  @Test
  void failWhenSetNullProperty() {
    assertThrows(
        RuntimeException.class,
        () -> PropertiesEnum.NOTION_API_URL.setValue(null)
    );
  }

  @Test
  void setPropertyValueWithSuccess() {
    assertNull(System.getProperty(PropertiesEnum.NOTION_API_URL.getPropertyName()));
    String myUrl = "https://my-url.com";
    PropertiesEnum.NOTION_API_URL.setValue(myUrl);

    assertEquals(myUrl, PropertiesEnum.NOTION_API_URL.value());
  }

  @Test
  void doNotSetPropertyValueWhenNotEmpty() {
    String myUrl = "https://my-url-2.com";
    System.setProperty(PropertiesEnum.NOTION_API_URL.getPropertyName(), myUrl);

    PropertiesEnum.NOTION_API_URL.setValueIfPropertyEmpty("https://new-url.com");

    assertEquals(myUrl, PropertiesEnum.NOTION_API_URL.value());
  }
}
