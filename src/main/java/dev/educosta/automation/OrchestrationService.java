package dev.educosta.automation;

import static dev.educosta.automation.notion.NotionQueryDatabaseRequest.createRequestForQuerySharedAt;

import dev.educosta.automation.linkedin.LinkedinService;
import dev.educosta.automation.linkedin.SharingContent;
import dev.educosta.automation.notion.NotionQueryDatabaseResponse.Result;
import dev.educosta.automation.notion.NotionService;
import dev.educosta.automation.notion.NotionUpdatePageRequest;
import dev.educosta.automation.notion.NotionUpdatePageRequest.LogColor;
import dev.educosta.automation.twitter.TwitterService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.Pair;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class OrchestrationService {

  private LinkedinService linkedinService;
  private TwitterService twitterService;
  private NotionService notionService;

  public OrchestrationService() {
    this.linkedinService = new LinkedinService();
    this.twitterService = new TwitterService();
    this.notionService = new NotionService();
  }

  public void requestToken() {
    linkedinService.requestToken();
  }

  public void readDataFromNotionAndPostOnSocialMedia() {
    log.info("Starting reading data for sharing");
    LocalDate currentDate = getCurrentDateTime().toLocalDate();
    var notionQueryDatabaseRequest = createRequestForQuerySharedAt(currentDate);
    log.info("Reading Notion database selecting pages at date {}.", currentDate);
    var notionResponse = notionService.query(notionQueryDatabaseRequest);
    log.info("{} page(s) read from Notion.", notionResponse.getResults().size());
    List<Result> pagesThatShouldBeShared = filterPagesThatShouldBeShared(notionResponse.getResults());
    log.info("{} page(s) selected to be shared.", pagesThatShouldBeShared.size());
    for (Result page : pagesThatShouldBeShared) {
      var updatePageRequest = NotionUpdatePageRequest.builder().sharedAt(ZonedDateTime.now());
      String id = page.getId().replace("-", "");
      String sharedText = page.getProperties().getShareText().getFormula().getString();
      log.info("Page [{}] Text: \n{}", page.getUrl(), sharedText);
      shareOnLinkedin(updatePageRequest, sharedText);
      shareOnTwitter(updatePageRequest, sharedText);
      notionService.updatePage(id, updatePageRequest.build());
    }
    log.info("Finished sharing");
  }

  private void shareOnLinkedin(NotionUpdatePageRequest.NotionUpdatePageRequestBuilder updatePageRequest, String sharedText) {
    try {
      linkedinService.post(new SharingContent(sharedText));
      updatePageRequest.sharedOn("linkedin");
    } catch (Exception e) {
      String tagError = "linkedin-error";
      log.error(tagError, e);
      updatePageRequest.sharedOn(tagError);
      updatePageRequest.addLog(tagError + ": " + e.getMessage(), LogColor.RED);
    }
  }

  private void shareOnTwitter(NotionUpdatePageRequest.NotionUpdatePageRequestBuilder updatePageRequest, String sharedText) {
    try {
      twitterService.post(sharedText);
      updatePageRequest.sharedOn("twitter");
    } catch (Exception e) {
      String tagError = "twitter-error";
      log.error(tagError, e);
      updatePageRequest.sharedOn(tagError);
      updatePageRequest.addLog(tagError + ": " + e.getMessage(), LogColor.RED);
    }
  }

  /**
   * Pages should be between NOW and NOW + INTERVAL minutes to be selected.
   */
  List<Result> filterPagesThatShouldBeShared(List<Result> results) {
    var period = getPeriod();
    log.info("Filtering pages between {} and {}", period.getFirst(), period.getSecond());
    return results.stream().filter(resultPage ->
        resultPage.isBetween(period.getFirst(), period.getSecond())
    ).collect(Collectors.toList());
  }


  LocalDateTime getCurrentDateTime() {
    return LocalDateTime.now();
  }

  /**
   * Create a period with start date time with current time and end date time with "interval" value ahead.
   */
  Pair<LocalDateTime, LocalDateTime> getPeriod() {
    return new Pair<>(getCurrentDateTime(), getCurrentDateTime().plusMinutes(TokenPropertiesEnum.getIntervalMinutes()));
  }

}
