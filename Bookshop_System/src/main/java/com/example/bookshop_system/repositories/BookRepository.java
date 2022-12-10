package com.example.bookshop_system.repositories;

import com.example.bookshop_system.model.AuthorNamesWithTotalCopies;
import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.model.BookSummary;
import com.example.bookshop_system.model.enums.AgeRestriction;
import com.example.bookshop_system.model.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<Book> findBookTitleByTitleContainingIgnoreCase(String str);

    List<Book> findAllByAuthorLastNameStartsWith(String str);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length")
    Integer bookCountByTitleLongerThan(int length);

    @Query("SELECT a.firstName as firstName,a.lastName as lastName, SUM(b.copies) as totalCopies" +
            " from Author a" +
            " join a.books AS b" +
            " group by b.author" +
            " order by totalCopies DESC")
    List<AuthorNamesWithTotalCopies> findCopiesByAuthorFirstNameAndAuthorLastName(String firstName, String lastName);

    List<BookSummary> findBookInfoByTitle(String title);


    @Query("UPDATE Book b SET b.copies = b.copies + :amount WHERE b.releaseDate > :date")
    long increaseBookCopiesAfterDate(LocalDate date, int amount);
}

