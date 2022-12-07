package com.example.advanced_quering_ex.service;


import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategories() throws IOException;
}
