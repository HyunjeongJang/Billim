package com.web.billim.order.repository;

import com.web.billim.order.domain.ProductOrder;
import com.web.billim.product.domain.Product;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findAllByProductAndEndAtAfter(Product product, LocalDate now);




}

