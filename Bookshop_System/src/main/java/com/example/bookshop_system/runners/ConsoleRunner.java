package com.example.bookshop_system.runners;

import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.repositories.AuthorRepository;
import com.example.bookshop_system.services.AuthorService;
import com.example.bookshop_system.services.BookService;
import com.example.bookshop_system.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService SeedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

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
        seedDatabase();
        //bookService.printBookTitlesAfter2000();
        //authorService.printAuthorsBefore1990();
        //authorService.printAuthorsOrderedByBooksCount();
        authorService.printGeorgePowellBookOrdered();
    }
}
