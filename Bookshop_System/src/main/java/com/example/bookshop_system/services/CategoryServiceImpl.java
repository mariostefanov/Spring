package com.example.bookshop_system.services;

import com.example.bookshop_system.model.Category;
import com.example.bookshop_system.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        long size = this.categoryRepository.count();


        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Long> categoriesIds = new HashSet<>();

        for (int i = 0; i < categoriesCount; i++) {
            long nextId = random.nextInt((int) size) + 1;
            categoriesIds.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(allById);
    }
}
