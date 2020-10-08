package com.eigenbaumarkt.spring5mvc.restapplication.bootstrap;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // from CommandLineRunner, will run on every Application Startup
    @Override
    public void run(String... args) throws Exception {

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        Category fruits = new Category();
        fruits.setName("Fruits");

        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        categoryRepository.save(fruits);

        log.info("Data loaded: " + categoryRepository.count() + " preset Categories");

    }
}
