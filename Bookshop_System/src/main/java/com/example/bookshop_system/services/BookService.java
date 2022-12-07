package com.example.bookshop_system.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookService {
    void printBookTitlesAfter2000();

    void printAllByAgeRestriction(String ageRestriction);

    void printAllGoldenEditionsLessThan5000Copies();

    void printAllByPrice(BigDecimal greater, BigDecimal less);

    void printAllNotReleasedInYear(int year);

    void printAllBeforeReleasedDate(LocalDate date);
}
