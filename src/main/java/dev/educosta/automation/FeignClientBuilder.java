package dev.educosta.automation;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public final class FeignClientBuilder {

  public static <T> T createClient(Class<T> type, String uri) {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new JacksonEncoder())
        .decoder(new JacksonDecoder())
        .logger(new Slf4jLogger(type))
        .logLevel(Logger.Level.FULL)
        .target(type, uri);
  }
}
