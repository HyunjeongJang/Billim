package com.web.billim.repository;

import com.web.billim.domain.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
}
