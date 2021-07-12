package dev.educosta.automation.controllers;


import static dev.educosta.automation.controllers.FeignClientBuilder.createClient;

import dev.educosta.automation.clients.BookClient;
import lombok.Getter;


@Getter
public class BookControllerFeignClientBuilder {

  private final BookClient feignClient = createClient(
      BookClient.class,
      "http://localhost:8080/api/books"
  );


}