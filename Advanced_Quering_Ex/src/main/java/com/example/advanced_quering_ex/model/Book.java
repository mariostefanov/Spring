package com.example.advanced_quering_ex.model;

import com.example.advanced_quering_ex.model.enums.AgeRestriction;
import com.example.advanced_quering_ex.model.enums.EditionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne(targetEntity = Author.class)
    private Author author;

    @ManyToMany
    Set<Category> categories;

    public Book() {
    }

    public Book(String title, EditionType editionType, BigDecimal price, int copies, LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public Book setEditionType(EditionType editionType) {
        this.editionType = editionType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public Book setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Book setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public Book setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Book setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }
}
