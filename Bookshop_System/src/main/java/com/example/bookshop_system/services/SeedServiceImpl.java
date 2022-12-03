package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Author;
import com.example.bookshop_system.model.Book;
import com.example.bookshop_system.model.Category;
import com.example.bookshop_system.model.enums.AgeRestriction;
import com.example.bookshop_system.model.enums.EditionType;
import com.example.bookshop_system.repositories.AuthorRepository;
import com.example.bookshop_system.repositories.BookRepository;
import com.example.bookshop_system.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService{


    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String AUTHORS_FILE_PATH = RESOURCES_FILE_PATH + "authors.txt";
    private static final String BOOKS_FILE_PATH = RESOURCES_FILE_PATH + "books.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCES_FILE_PATH + "categories.txt";
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public SeedServiceImpl(AuthorRepository authorRepository, AuthorService authorService, BookRepository bookRepository, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(names -> new Author(names[0], names[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);
    }

    private Book getBookObject(String line) {
        String[] bookParts = line.split(" ");

        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];

        LocalDate publishDate =
                LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);

        int ageRestrictionIndex = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(bookParts)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, publishDate,
                ageRestriction, author, categories);
    }
}
