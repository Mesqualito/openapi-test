package com.eigenbaumarkt.spring5mvc.restapplication.bootstrap;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Vendor;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CategoryRepository;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CustomerRepository;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
    }

    // from CommandLineRunner, will run on every Application Startup
    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadVendors();
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

        log.info("Data loaded in Bootstrap class: " + categoryRepository.count() + " preset categories.");

    }


    private void loadVendors() {

        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor 1");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Vendor 2");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        log.info("Data loaded in Bootstrap class: " + vendorRepository.count() + " preset vendors.");



    }

    private void loadCustomers() {

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

        log.info("Data loaded in Bootstrap class: " + customerRepository.count() + " preset customers.");

    }
}
