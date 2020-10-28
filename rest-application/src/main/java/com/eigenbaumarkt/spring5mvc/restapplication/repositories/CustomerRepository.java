package com.eigenbaumarkt.spring5mvc.restapplication.repositories;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Customer findByFirstnameAndLastname(String firstname, String lastname);
}
