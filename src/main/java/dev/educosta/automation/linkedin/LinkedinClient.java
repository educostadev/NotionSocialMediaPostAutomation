package dev.educosta.automation.linkedin;

import com.echobox.api.linkedin.client.DefaultLinkedInClient;
import com.echobox.api.linkedin.connection.v2.ShareConnection;
import com.echobox.api.linkedin.types.ContentEntity;
import com.echobox.api.linkedin.types.Share;
import com.echobox.api.linkedin.types.ShareContent;
import com.echobox.api.linkedin.types.ShareText;
import com.echobox.api.linkedin.types.request.ShareRequestBody;
import com.echobox.api.linkedin.types.urn.URN;
import com.github.scribejava.apis.LinkedInApi20;
import com.github.scribejava.core.builder.ScopeBuilder;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import dev.educosta.automation.TokenPropertiesEnum;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class LinkedinClient {

  private static final String PROTECTED_RESOURCE_URL = "https://api.linkedin.com/v2/me";

  private final String accessToken;
  private final String apiKey;
  private final String apiSecret;
  private final String callBackUrl;
  private final String userId;

  public LinkedinClient(String apiKey, String apiSecret, String callBackUrl, String accessToken,
      String userId) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.callBackUrl = callBackUrl;
    this.accessToken = accessToken;
    this.userId = userId;
  }


  @SneakyThrows
  public void post(SharingContent sharingContent) {

    ShareRequestBody shareRequestBody = createShareRequestBody();

    if (sharingContent.getUrl() != null) {
      ShareContent shareContent = new ShareContent();
      ContentEntity contentEntity = new ContentEntity();
      contentEntity.setEntityLocation(sharingContent.getUrl());
      shareContent.setContentEntities(Arrays.asList(contentEntity));
      shareRequestBody.setContent(shareContent);
    }

    ShareText shareText = new ShareText();
    shareText.setText(sharingContent.getText());
    shareRequestBody.setText(shareText);

    ShareConnection shareConnection = createConnection();

    if (TokenPropertiesEnum.shouldPostOnLinkedin()) {
      Share share = shareConnection.postShare(shareRequestBody);
      log.info("Shared to linkedin [{}]", share.getId());
    } else {
      log.info("Shared to linkedin SKIPPED.");
    }

  }

  @NotNull
  private ShareRequestBody createShareRequestBody() {
    return new ShareRequestBody(
        new URN(MessageFormat.format("urn:li:person:{0}", userId)));
  }


  public ShareConnection createConnection() throws GeneralSecurityException, IOException {
    return new ShareConnection(new DefaultLinkedInClient(accessToken));
  }

  @SneakyThrows
  public void requestToken() {
    OAuth20Service service = new ServiceBuilder(apiKey)
        .apiSecret(apiSecret)
        .defaultScope(new ScopeBuilder("r_liteprofile", "r_emailaddress", "w_member_social"))
        .callback(callBackUrl)
        .build(LinkedInApi20.instance());

    final Scanner in = new Scanner(System.in);

    log.info("Fetching the Authorization URL...");
    final String secretState = "secret" + new Random().nextInt(999_999);
    final String authorizationUrl = service.getAuthorizationUrl(secretState);
    log.info("Got the Authorization URL!");
    log.info("Now go and authorize ScribeJava here:");
    log.info(authorizationUrl);
    log.info("And paste the authorization code here");
    System.out.print(">>");
    String code = in.nextLine();

    log.info("Trading the Authorization Code for an Access Token...");
    final OAuth2AccessToken accessToken = service.getAccessToken(code);
    log.info("Got the Access Token!");
    log.info("(The raw response looks like this: " + accessToken.getRawResponse() + "')");

    log.info("Now we're going to access a protected profile resource...");

    final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    request.addHeader("x-li-format", "json");
    request.addHeader("Accept-Language", "ru-RU");
    service.signRequest(accessToken, request);

    try (Response response = service.execute(request)) {
      log.info("[{}] {}", response.getCode(), response.getBody());
    }

  }


}