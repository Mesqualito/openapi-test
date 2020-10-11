package com.eigenbaumarkt.spring5mvc.restapplication.repositories;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByName(String name);
}
