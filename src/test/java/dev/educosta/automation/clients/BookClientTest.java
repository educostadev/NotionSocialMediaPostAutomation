package dev.educosta.automation.clients;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.educosta.automation.AbstractIntegrationTest;
import dev.educosta.automation.utils.ReadFileAsString;
import dev.educosta.automation.controllers.BookControllerFeignClientBuilder;
import dev.educosta.automation.models.Book;
import dev.educosta.automation.models.BookResource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@Slf4j
class BookClientTest extends AbstractIntegrationTest
    implements ReadFileAsString {

  private BookClient feignClient;


  @BeforeEach
  public void setup() {
    BookControllerFeignClientBuilder feignClientBuilder = new BookControllerFeignClientBuilder();
    feignClient = feignClientBuilder.getFeignClient();
  }

  @Test
  void givenBookClient_shouldRunSuccessfully() {
    stubGetFor("/api/books", readJson("books.json"));
    List<Book> books = feignClient.findAll()
        .stream()
        .map(BookResource::getBook)
        .collect(Collectors.toList());
    assertTrue(books.size() >= 2);
    log.info("{}", books);
  }

  @Test
  void givenBookClient_shouldFindOneBook() {
    String isbn = "1447264533";
    stubGetFor("/api/books/" + isbn, readJson("book.json"));
    Book book = feignClient.findByIsbn(isbn)
        .getBook();

    assertThat(book.getAuthor(), containsString("Margaret Mitchell"));
    log.info("{}", book);
  }

  //@Test
  public void givenBookClient_shouldPostBook() {
    String isbn = UUID.randomUUID()
        .toString();
    Book book = new Book(isbn, "Me", "It's me!", null, null);
    feignClient.create(book);

    book = feignClient.findByIsbn(isbn)
        .getBook();
    assertThat(book.getAuthor(), is("Me"));
    log.info("{}", book);
  }
}