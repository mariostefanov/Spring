package com.example.bookshop_system.repositories;

import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.model.enums.AgeRestriction;
import com.example.bookshop_system.model.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByReleaseDateIsAfter(LocalDate date);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String fistName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllTitlesByEditionTypeAndCopiesLessThan(EditionType editionType,int copies);

    List<Book> findAllByPriceGreaterThanOrPriceLessThan(BigDecimal greater, BigDecimal less);

    List<Book> findAllByReleaseDateIsBeforeOrReleaseDateAfter(LocalDate before,LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllFirstNameEdsWith(String ends);
}
