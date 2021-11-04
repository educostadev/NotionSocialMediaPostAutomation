package dev.educosta.automation.linkedin;

import java.net.MalformedURLException;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SharingContent {

  @Getter
  private final String text;

  public String getUrl() {
    String[] lines = text.split("\n");
    try {
      return new URL(lines[lines.length - 1]).toString();
    } catch (MalformedURLException e) {
      return null;
    }
  }


}
