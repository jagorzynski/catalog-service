package com.polarbookshop.catalogservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.time.Instant;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @CreatedDate Instant createdDate;
  @LastModifiedDate Instant lastModifiedDate;

  @NotBlank(message = "The book ISBN must be defined.")
  @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.")
  String isbn;

  @NotBlank(message = "The book title must be defined.")
  String title;

  @NotBlank(message = "The book author must be defined.")
  String author;

  @NotNull(message = "The book price must be defined.")
  @Positive(message = "The book price must be greater than zero.")
  Double price;

  @Version int version;

  String publisher;

  public static Book of(String isbn, String title, String author, Double price, String publisher) {
    return Book.builder().isbn(isbn).title(title).author(author).price(price).publisher(publisher).build();
  }
}
