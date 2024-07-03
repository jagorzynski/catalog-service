package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.polarbookshop.catalogservice.config.JpaConfig;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class BookRepositoryTest {

  @Autowired private BookRepository bookRepository;
  @Autowired private EntityManager entityManager;

  @Test
  void findBookByIsbnWhenExisting() {
    var bookIsbn = "1234561237";
    var book = Book.of(bookIsbn, "Title", "Author", 12.90, "PolarPublishing");
    entityManager.persist(book);
    Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
    assertThat(actualBook).isPresent();
    assertThat(actualBook.get().getIsbn()).isEqualTo(book.getIsbn());
  }
}
