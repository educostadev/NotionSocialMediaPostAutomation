package dev.educosta.automation.linkedin;

import static org.mockito.Mockito.mock;

import dev.educosta.automation.TokenPropertiesEnum;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LinkedinServiceTest {

  static final String FAKE_API_KEY = "DUMMY";

  @BeforeAll
  static void init() {
    TokenPropertiesEnum.LINKEDIN_API_KEY.setValueIfPropertyEmpty(FAKE_API_KEY);
    TokenPropertiesEnum.LINKEDIN_API_SECRET.setValueIfPropertyEmpty("xxx");
    TokenPropertiesEnum.LINKEDIN_CALLBACK_URL.setValueIfPropertyEmpty("xxx");
    TokenPropertiesEnum.LINKEDIN_ACCESS_TOKEN.setValueIfPropertyEmpty("xxx");
    TokenPropertiesEnum.LINKEDIN_USER_ID.setValueIfPropertyEmpty("xxx");
  }

  @AfterAll
  static void end() {
    TokenPropertiesEnum.clearAllProperties();
  }

  @Test
  void shouldShareMediaOnLinkedinWithSuccess() {

    SharingContent content = new SharingContent("Title.\nText Text.\n\n#tag\nhttp://educosta.dev");
    LinkedinService service;

    if (shouldPostOnMockAccount()) {
      service = mock(LinkedinService.class);
    } else {
      service = new LinkedinService();
    }

    service.post(content);
  }

  /**
   * When a fake api key is being used we will use mock twitter object and assert the call.
   */
  private boolean shouldPostOnMockAccount() {
    return TokenPropertiesEnum.LINKEDIN_API_KEY.value().equals(FAKE_API_KEY);
  }

}