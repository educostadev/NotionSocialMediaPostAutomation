package dev.educosta.automation;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * JMV Arguments:
 * <p>
 * Linkedin: -DlinkedinUserId=X -DlinkedinAPIKey=X -DlinkedinAPISecret=X -DlinkedinCallbackURL=X -DaccessToken=X
 */
public enum TokenPropertiesEnum {

  NOTION_TOKEN("notionBearerToken"),
  NOTION_SHARE_DATABASE_ID("notionShareDatabaseId"),
  NOTION_API_URL("notionApiUrl"),
  TWITTER_API_KEY("twitterApiKey"),
  TWITTER_API_SECRET_KEY("twitterApiSecretKey"),
  TWITTER_ACCESS_TOKEN("twitterAccessToken"),
  TWITTER_ACCESS_TOKEN_SECRET("twitterAccessTokenSecret"),
  /**
   * User id is get by calling the profile using the API. Run the request token
   */
  LINKEDIN_USER_ID("linkedinUserId"),
  /**
   * The same from the linkedin api portal
   */
  LINKEDIN_API_KEY("linkedinAPIKey"),
  /**
   * The same from the linkedin app portal
   */
  LINKEDIN_API_SECRET("linkedinAPISecret"),
  /**
   * The same url configured at linkedin app portal
   */
  LINKEDIN_CALLBACK_URL("linkedinCallbackURL"),
  /**
   * Acquired after access the authorization using the browser
   */
  LINKEDIN_ACCESS_TOKEN("linkedinAccessToken");


  final String jvmPropertyName;

  TokenPropertiesEnum(String jvmPropertyName) {
    this.jvmPropertyName = jvmPropertyName;
  }

  public String value() {
    return Optional.ofNullable(System.getProperty(jvmPropertyName))
        .orElseThrow(() -> new NullPointerException(
            format(
                "JVM property {0} not specified. Add the parameter -D{0}=VALUE on the command line.",
                jvmPropertyName))
        );
  }

  public void setValue(String value) {
    System.setProperty(jvmPropertyName, requireNonNull(value));
  }


  public void setValueIfPropertyEmpty(String value) {
    Optional.ofNullable(System.getProperty(jvmPropertyName)).ifPresentOrElse(
        doNothing -> {
        }
        , () -> setValue(value)
    );
  }

  public static void clearAllProperties() {
    Stream.of(values()).forEach(value -> System.clearProperty(value.jvmPropertyName));
  }

  /**
   * Define how many minutes ahead should be considered to select pages from notion. Default value is 5 minutes.
   */
  public static int getIntervalMinutes() {
    return Integer.parseInt(System.getProperty("interval", "5"));
  }

  /**
   * Turn off/on the last step to post on linkedin.
   */
  public static boolean shouldPostOnLinkedin() {
    return Boolean.parseBoolean(System.getProperty("shouldPostOnLinkedin", "TRUE"));
  }

  /**
   * Turn off/on the last step to post on linkedin.
   */
  public static boolean shouldPostOnTwitter() {
    return Boolean.parseBoolean(System.getProperty("shouldPostOnTwitter", "TRUE"));
  }

  /**
   * Used to request a new linkedin token only.
   */
  public static boolean requestLinkedinTokenOnly() {
    return Boolean.parseBoolean(System.getProperty("requestLinkedinTokenOnly", "FALSE"));
  }

}
