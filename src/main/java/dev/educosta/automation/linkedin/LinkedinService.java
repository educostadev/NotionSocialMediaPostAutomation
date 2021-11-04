package dev.educosta.automation.linkedin;

import dev.educosta.automation.TokenPropertiesEnum;

public class LinkedinService {

  private final LinkedinClient linkedinClient;

  public LinkedinService() {
    this.linkedinClient = new LinkedinClient(
        TokenPropertiesEnum.LINKEDIN_API_KEY.value(),
        TokenPropertiesEnum.LINKEDIN_API_SECRET.value(),
        TokenPropertiesEnum.LINKEDIN_CALLBACK_URL.value(),
        TokenPropertiesEnum.LINKEDIN_ACCESS_TOKEN.value(),
        TokenPropertiesEnum.LINKEDIN_USER_ID.value()
    );
  }

  public void post(SharingContent sharingContent) {
    linkedinClient.post(sharingContent);
  }

  public void requestToken(){
    linkedinClient.requestToken();
  }

}