package com.web.billim.order.repository;

import com.web.billim.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    Optional<Order> findByProductId(int productId);


}

