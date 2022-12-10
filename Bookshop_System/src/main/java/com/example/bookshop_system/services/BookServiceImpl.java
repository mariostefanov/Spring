package com.example.bookshop_system.services;


import com.example.bookshop_system.model.AuthorNamesWithTotalCopies;
import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.model.BookSummary;
import com.example.bookshop_system.model.enums.AgeRestriction;
import com.example.bookshop_system.model.enums.EditionType;
import com.example.bookshop_system.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void printBookTitlesAfter2000() {
        List<Book> bookAfter2000 = bookRepository.findAllByReleaseDateIsAfter(LocalDate.of(2000, 12, 31));
         bookAfter2000.stream().map(Book::getTitle).forEach(System.out::println);

    }

    @Override
    public void printAllByAgeRestriction(String ageRestriction) {
        List<Book> booksByAgeRestriction = this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));

        booksByAgeRestriction.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllGoldenEditionsLessThan5000Copies() {
        List<Book> books = this.bookRepository.findAllTitlesByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);

        for (Book b : books) {
            System.out.println(b.getTitle());
        }
    }

    @Override
    public void printAllByPrice(BigDecimal lower,BigDecimal higher) {
        List<Book> books = bookRepository.findAllByPriceGreaterThanOrPriceLessThan(lower, higher);

        books.forEach(book -> System.out.println(book.getTitle() + "-" + book.getPrice()));
    }

    @Override
    public void printAllNotReleasedInYear(int year) {
        LocalDate before = LocalDate.of(year,1,1);
        LocalDate after = LocalDate.of(year, 12,31);
        List<Book> books = bookRepository.findAllByReleaseDateIsBeforeOrReleaseDateAfter(before, after);

        books.forEach(b -> System.out.println(b.getTitle() + " - " + b.getReleaseDate()));
    }

    @Override
    public void printAllBeforeReleasedDate(LocalDate date) {
        List<Book> books = bookRepository.findAllByReleaseDateBefore(date);

        books.forEach(book -> System.out.println(book.getTitle()+ " - " + book.getEditionType() + " - " + book.getPrice()));
    }

    @Override
    public void printBookTitlesContaining(String str) {
        List<Book> titles = bookRepository.findBookTitleByTitleContainingIgnoreCase(str);
        titles.forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllTitleByAuthorLastNameStarts(String str) {
        List<Book> books = bookRepository.findAllByAuthorLastNameStartsWith(str);

        books.forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printBooksTitlesLongerThan(int n) {
        Integer count = bookRepository.bookCountByTitleLongerThan(n);

        System.out.println(count);
    }

    @Override
    public void printCopiesByAuthor(String firstName, String lastName) {
        List<AuthorNamesWithTotalCopies> namesAndTotalCopies = bookRepository.findCopiesByAuthorFirstNameAndAuthorLastName(firstName, lastName);
        System.out.println();
        namesAndTotalCopies.forEach(n -> System.out.println(n.getFirstName() + " " + n.getLastName() + " " + n.getTotalCopies()));
    }

    @Override
    public List<BookSummary> findBookInfoByTitle(String title) {
        return bookRepository.findBookInfoByTitle(title);
    }

    @Override
    public long increaseBookCopiesAfterDate(String date, int amount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate after = LocalDate.parse(date, formatter);
        return this.bookRepository.increaseBookCopiesAfterDate(after,amount);
    }


}
