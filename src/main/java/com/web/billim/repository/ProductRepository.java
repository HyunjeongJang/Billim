package com.web.billim.repository;

import com.web.billim.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByMember_memberId(int memberId);

//     JPQL
//     @Query("SELECT p FROM Product p WHERE p.member.memberId = :memberId")
//     List<Product> findByMemberId(@Param("memberId") int memberId);

//    Page<Product> findByProductNameAndDetailContaining(String keyword, Pageable pageable);

}



