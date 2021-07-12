package dev.educosta.automation.controllers;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public final class FeignClientBuilder {

  public static <T> T createClient(Class<T> type, String uri) {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new Slf4jLogger(type))
        .logLevel(Logger.Level.FULL)
        .target(type, uri);
  }
}
