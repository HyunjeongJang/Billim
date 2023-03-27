package com.web.billim.repository;

import com.web.billim.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 이것도 진짜 SQL 이 아니다. JPQL 이라고 불리는 JAVA 기반 쿼리.
    // 컴파일 시점에 쿼리가 잘못된 것을 조기에 발견할 수 있다 -> 안정적인 쿼리
    // 진짜 복잡한 쿼리 -> JPQL x -> QueryDSL
    // @Query("SELECT p FROM Product p WHERE p.area = :area") // 복잡한 건 직접 짠다. N + 1 문제 -> Fetch Join -> 직접 쿼리
    // List<Product> findAllByArea(String area);
    // 내가 만든 메소드 이름으로 쿼리가 만들어진다.

}

// Spring Data -> Repository
// 추상 메소드를 만들어 놓으면 -> 그거에 맞는 구현체(쿼리 포함)를 직접 만들어줌.

