package dev.educosta.automation.notion;

import dev.educosta.automation.linkedin.SharingContent;
import java.text.MessageFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SharingContentTest {


  @Test
  void extractUrlFromTextWithSuccess() {
    String myUrl = "http://dummy.com";
    String text = MessageFormat.format("abc \n\n{0} ", myUrl);
    SharingContent content = new SharingContent(text);

    Assertions.assertEquals(myUrl, content.getUrl());
  }

  @Test
  void extractUrlFromText2WithSuccess() {

    SharingContent content = new SharingContent("Title.\nText Text.\n\n#tag\nhttp://educosta.dev");

    Assertions.assertEquals("http://educosta.dev", content.getUrl());
  }

  @Test
  void shouldNotDetectURLWhenTestDoesNotHaveUrl() {
    String myUrl = "";
    String text = MessageFormat.format("abc {0} abc", myUrl);
    SharingContent content = new SharingContent(text);

    Assertions.assertNull(content.getUrl());
  }

  @Test
  void shouldSelectLastUrlFromTextWithMultipleUrls() {
    String url2 = "https://www.URL2.com/article";
    String myUrl = "http://URL21.com \n" + url2;
    String text = MessageFormat.format("abc {0}", myUrl);
    SharingContent content = new SharingContent(text);

    Assertions.assertEquals(url2, content.getUrl());
  }

}