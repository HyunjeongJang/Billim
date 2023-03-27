package com.web.billim.repository;

import com.web.billim.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<Product, Long> {
}
