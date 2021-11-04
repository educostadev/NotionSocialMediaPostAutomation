package dev.educosta.automation.twitter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.dto.user.User;
import dev.educosta.automation.TokenPropertiesEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This test can fire request direct on twitter when the properties -Dxx are passed as jvm argument with tokes from you account.
 * -DtwitterApiKey=<value> -DtwitterApiSecretKey=<value> -DtwitterAccessToken=<value> -DtwitterAccessTokenSecret=<value>
 */
class TwitterServiceTest {

  static final String FAKE_TWITTER_API_KEY = "qfFxxxXXXxxxXXXxxxXXXxWAD";

  @BeforeAll
  static void init() {
    TokenPropertiesEnum.TWITTER_API_KEY.setValueIfPropertyEmpty(FAKE_TWITTER_API_KEY);
    TokenPropertiesEnum.TWITTER_API_SECRET_KEY.setValueIfPropertyEmpty("hadXXXxxxXXh5MiXXXxxxxXXX3R4hnXXxxxXXXCIe1XXxxxXXX");
    TokenPropertiesEnum.TWITTER_ACCESS_TOKEN.setValueIfPropertyEmpty("1300000200805000030-dKXXXxxxXXXxxxdyXXXxxxXXXxxxnA");
    TokenPropertiesEnum.TWITTER_ACCESS_TOKEN_SECRET.setValueIfPropertyEmpty("1KxxxXXXxxxXXX1IvXXXxxxXXXrtxxx3aXXXxxxXXXxxl");
  }

  @Test
  void shouldPostTweetWithSuccess() {
    //GIVEN
    var service = new TwitterService();
    String tweetText = "Know my blog. \n Check it out: https://educosta.dev/ \n\n #java #cloud";
    var mockTwitter = mock(TwitterClient.class);
    if (shouldPostOnMockAccount()) {
      service.setTwitterClient(mockTwitter);
      when(mockTwitter.getUserFromUserId(any())).thenReturn(mock(User.class));
      when(mockTwitter.postTweet(tweetText)).thenReturn(mock(Tweet.class));
    }

    //WHEN
    service.post(tweetText);

    //THEN
    if (shouldPostOnMockAccount()) {
      verify(mockTwitter, times(1)).postTweet(tweetText);
    }
  }

  /**
   * When a fake api key is being used we will use mock twitter object and assert the call.
   */
  private boolean shouldPostOnMockAccount() {
    return TokenPropertiesEnum.TWITTER_API_KEY.value().equals(FAKE_TWITTER_API_KEY);
  }

}