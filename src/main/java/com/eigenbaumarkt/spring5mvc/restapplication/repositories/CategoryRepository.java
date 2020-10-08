package com.eigenbaumarkt.spring5mvc.restapplication.repositories;

import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
