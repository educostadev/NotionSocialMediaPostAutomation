package dev.educosta.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.educosta.automation.linkedin.LinkedinService;
import dev.educosta.automation.notion.NotionQueryDatabaseResponse;
import dev.educosta.automation.notion.NotionService;
import dev.educosta.automation.twitter.TwitterService;
import dev.educosta.automation.utils.ReadFileAsString;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrchestrationServiceTest implements ReadFileAsString {

  ObjectMapper mapper =
      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @BeforeAll
  static void init() {
    Stream.of(TokenPropertiesEnum.values())
        .forEach(properties -> properties.setValue(UUID.randomUUID().toString()));
  }

  @AfterAll
  static void end() {
    TokenPropertiesEnum.clearAllProperties();
  }

  @Test
  void shouldSelectResultWithinTheTimePeriod() throws JsonProcessingException {
    String json = readJson("notionQueryDatabaseResponse.json");
    var response = mapper.readValue(json, NotionQueryDatabaseResponse.class);

    OrchestrationService orchestrationService = new OrchestrationService() {
      @Override
      LocalDateTime getCurrentDateTime() {
        return LocalDateTime.of(2021, 7, 6, 11, 0, 0);
      }
    };

    var result = orchestrationService.filterPagesThatShouldBeShared(response.getResults());

    assertEquals(2, result.size());

  }


  @SneakyThrows
  @Test
  void shouldReadDataFromNotionAndPostOnTwitterAndLinkedin() {
    String json = readJson("notionQueryDatabaseResponse.json");
    var response = mapper.readValue(json, NotionQueryDatabaseResponse.class);

    NotionService notionService = mock(NotionService.class);
    when(notionService.query(any())).thenReturn(response);

    LinkedinService linkedinService = mock(LinkedinService.class);
    TwitterService twitterService = mock(TwitterService.class);

    OrchestrationService orchestrationService = new OrchestrationService() {
      @Override
      LocalDateTime getCurrentDateTime() {
        return LocalDateTime.of(2021, 7, 6, 11, 0, 0);
      }
    };
    orchestrationService.setNotionService(notionService);
    orchestrationService.setTwitterService(twitterService);
    orchestrationService.setLinkedinService(linkedinService);

    orchestrationService.readDataFromNotionAndPostOnSocialMedia();

    verify(notionService, times(1)).query(any());
    verify(linkedinService, times(2)).post(any());
    verify(twitterService, times(2)).post(any());
    verify(notionService, times(2)).updatePage(any(), any());

  }
}