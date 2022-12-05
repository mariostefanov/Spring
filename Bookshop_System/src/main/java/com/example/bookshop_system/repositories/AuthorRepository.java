package com.example.bookshop_system.repositories;

import com.example.bookshop_system.model.Author;
import com.example.bookshop_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findByBooksReleaseDateBefore(LocalDate localDate);

    List<Book>  findAllBooksByFirstNameAndLastNameOrderByBooksReleaseDateDescBooksTitle(String firstName, String lastName);

}
