package dev.educosta.automation;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractIntegrationTest {

  static WireMockServer wireMockServer = new WireMockServer();

  @BeforeAll
  public static void start() {
    wireMockServer.start();
  }

  @AfterAll
  public static void stop() {
    wireMockServer.stop();
  }


  @SneakyThrows
  public static String toJson(Object obj) {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

  public void stubGetFor(String path, Object jsonResponse) {
    stubFor(
        get(urlPathEqualTo(path))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(toJson(jsonResponse))));
  }

  public void stubGetFor(String path, String jsonResponse) {
    stubFor(
        get(urlPathEqualTo(path))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(jsonResponse)));
  }

  public void stubPostFor(String path, String jsonResponse) {
    stubFor(
        post(urlPathEqualTo(path))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(jsonResponse)));
  }
}
