package com.eigenbaumarkt.spring5mvc.restapplication.bootstrap;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CategoryRepository;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    // from CommandLineRunner, will run on every Application Startup
    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();

    }

    private void loadCategories() {

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

    private void loadCustomers() {

        // given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Marina");
        customer1.setLastName("Jackson");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jochen");
        customer2.setLastName("Jarodna");
        customerRepository.save(customer2);

        log.info("Data loaded: " + customerRepository.count() + " customers.");

    }
}
