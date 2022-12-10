package com.example.bookshop_system.model;

import com.example.bookshop_system.model.enums.AgeRestriction;
import com.example.bookshop_system.model.enums.EditionType;

import java.math.BigDecimal;

public interface BookSummary {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
