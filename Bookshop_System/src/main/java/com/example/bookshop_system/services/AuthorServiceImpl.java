package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Author;
import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.repositories.AuthorRepository;
import com.example.bookshop_system.repositories.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        long authorId = new Random().nextInt((int) size) + 1;

        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public void printAuthorsBefore1990() {
        List<Author> authorsWithBooksBefore1990 = this.authorRepository.findByBooksReleaseDateBefore(LocalDate.of(1990, 1, 1));

        authorsWithBooksBefore1990.stream().map(a -> a.getFirstName() + " " + a.getLastName()).forEach(System.out::println);
    }

    @Override
    public void printAuthorsOrderedByBooksCount() {
        List<Author> authors = authorRepository.findAll();

        authors.stream()
                .sorted((l,r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n"
                        ,author.getFirstName()
                        ,author.getLastName()
                        ,author.getBooks().size())
                );
    }

    @Override
    public void printGeorgePowellBookOrdered() {
        List<Book> books = bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell");

        books.forEach(book ->
                        System.out.printf("%d Title:%s Names:%s %s Copies:%d%n%n",
                                book.getId(),
                                book.getTitle(),
                                book.getAuthor().getFirstName(),
                                book.getAuthor().getLastName(),
                                book.getCopies())

                );
    }

    @Override
    public void printFirstNamesEndsWith(String str) {
        List<Author> authors = authorRepository.findByFirstNameEndsWith(str);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }
}
