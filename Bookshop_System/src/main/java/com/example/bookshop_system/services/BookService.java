package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.model.BookSummary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void printBookTitlesAfter2000();

    void printAllByAgeRestriction(String ageRestriction);

    void printAllGoldenEditionsLessThan5000Copies();

    void printAllByPrice(BigDecimal greater, BigDecimal less);

    void printAllNotReleasedInYear(int year);

    void printAllBeforeReleasedDate(LocalDate date);

    void printBookTitlesContaining(String str);

    void printAllTitleByAuthorLastNameStarts(String str);

    void printBooksTitlesLongerThan(int n);

    void printCopiesByAuthor(String firstName, String lastName);

    List<BookSummary> findBookInfoByTitle(String title);

    long increaseBookCopiesAfterDate(String date, int amount);
}
