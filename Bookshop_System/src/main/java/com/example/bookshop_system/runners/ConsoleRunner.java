package com.example.bookshop_system.runners;

import com.example.bookshop_system.repositories.AuthorRepository;
import com.example.bookshop_system.services.AuthorService;
import com.example.bookshop_system.services.BookService;
import com.example.bookshop_system.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService SeedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService, AuthorRepository authorRepository) {
        SeedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    private void seedDatabase() throws IOException {
        this.SeedService.seedCategories();
        this.SeedService.seedAuthors();
        this.SeedService.seedBooks();
    }


    @Override
    public void run(String... args) throws Exception {
//        seedDatabase();
//        bookService.printAllByAgeRestriction("Minor");
//        bookService.printAllGoldenEditionsLessThan5000Copies();

//        BigDecimal greater = new BigDecimal("40");
//        BigDecimal less = new BigDecimal("5");
//        bookService.printAllByPrice(greater,less);

//        int year = 1998;
//        bookService.printAllNotReleasedInYear(year);

//        LocalDate date = LocalDate.of(1992,4,12);
//        bookService.printAllBeforeReleasedDate(date);


    }
}
