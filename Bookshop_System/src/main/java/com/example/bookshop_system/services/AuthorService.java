package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Author;

public interface AuthorService {
    Author getRandomAuthor();

    void printAuthorsBefore1990();

    void printAuthorsOrderedByBooksCount();

    void printGeorgePowellBookOrdered();

    void printFirstNamesEndsWith(String str);
}
