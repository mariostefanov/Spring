package com.example.bookshop_system.runners;

import com.example.bookshop_system.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private final SeedService SeedService;

    public ConsoleRunner(SeedService seedService) {
        SeedService = seedService;
    }

    private void seedDatabase() throws IOException {
        this.SeedService.seedCategories();
        this.SeedService.seedAuthors();
        this.SeedService.seedBooks();
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
        System.out.println();
    }
}
