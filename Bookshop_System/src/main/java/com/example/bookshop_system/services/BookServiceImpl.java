package com.example.bookshop_system.services;


import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
