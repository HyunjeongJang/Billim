package com.web.billim.order.repository;

import com.web.billim.order.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer> {

//    Optional<ProductOrder> findByProductId(int productId);


}

