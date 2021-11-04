package dev.educosta.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenPropertiesEnumTest {

  @BeforeEach
  void init() {
    TokenPropertiesEnum.clearAllProperties();
  }

  @Test
  void failWhenPropertyNotSpecified() {
    assertThrows(
        RuntimeException.class,
        TokenPropertiesEnum.NOTION_API_URL::value
    );
  }

  @Test
  void failWhenSetNullProperty() {
    assertThrows(
        RuntimeException.class,
        () -> TokenPropertiesEnum.NOTION_API_URL.setValue(null)
    );
  }

  @Test
  void setPropertyValueWithSuccess() {
    assertNull(System.getProperty(TokenPropertiesEnum.NOTION_API_URL.jvmPropertyName));
    String myUrl = "https://my-url.com";
    TokenPropertiesEnum.NOTION_API_URL.setValue(myUrl);

    assertEquals(myUrl, TokenPropertiesEnum.NOTION_API_URL.value());
  }

  @Test
  void doNotSetPropertyValueWhenNotEmpty() {
    String myUrl = "https://my-url-2.com";
    System.setProperty(TokenPropertiesEnum.NOTION_API_URL.jvmPropertyName, myUrl);

    TokenPropertiesEnum.NOTION_API_URL.setValueIfPropertyEmpty("https://new-url.com");

    assertEquals(myUrl, TokenPropertiesEnum.NOTION_API_URL.value());
  }
}
