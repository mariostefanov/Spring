package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Author;
import com.example.bookshop_system.repositories.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        long authorId = new Random().nextInt((int) size) + 1;

        return this.authorRepository.findById(authorId).get();
    }
}
