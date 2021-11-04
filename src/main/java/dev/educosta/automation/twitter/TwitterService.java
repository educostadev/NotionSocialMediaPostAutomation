package dev.educosta.automation.twitter;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import dev.educosta.automation.TokenPropertiesEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwitterService {

  @Setter
  private TwitterClient twitterClient;

  public TwitterService() {

    var twitterCredentials = TwitterCredentials.builder()
        .apiKey(TokenPropertiesEnum.TWITTER_API_KEY.value())
        .apiSecretKey(TokenPropertiesEnum.TWITTER_API_SECRET_KEY.value())
        .accessToken(TokenPropertiesEnum.TWITTER_ACCESS_TOKEN.value())
        .accessTokenSecret(TokenPropertiesEnum.TWITTER_ACCESS_TOKEN_SECRET.value()).build();

    twitterClient = new TwitterClient(twitterCredentials);
  }

  public void post(String tweetText) {
    if (TokenPropertiesEnum.shouldPostOnTwitter()) {
      var tweet = twitterClient.postTweet(tweetText);
      log.info("Shared to twitter [{}]", tweet.getId());
    } else {
      log.info("Shared to twitter SKIPPED.");
    }
  }


}
