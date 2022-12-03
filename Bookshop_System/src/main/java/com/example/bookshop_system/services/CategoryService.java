package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
