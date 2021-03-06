package dev.educosta;

import dev.educosta.automation.OrchestrationService;
import dev.educosta.automation.TokenPropertiesEnum;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

  private final OrchestrationService service;

  public Main() {
    this.service = new OrchestrationService();
  }

  void fire(String[] args) {

    boolean requestLinkedinTokenOnly = Arrays.asList(args).contains("requestLinkedinTokenOnly");
    if (requestLinkedinTokenOnly) {
      service.requestToken();
    } else {
      checkThatAllTokenParametersWasProvided();
      service.readDataFromNotionAndPostOnSocialMedia();
    }
  }

  private void checkThatAllTokenParametersWasProvided() {
    Stream.of(TokenPropertiesEnum.values()).forEach(TokenPropertiesEnum::value);
  }

  public static void main(String[] args) {
    new Main().fire(args);
  }
}
