package com.web.billim.service;

import com.web.billim.domain.Product;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product register(ProductRegisterRequest request) {
        // 1. 이미지 저장
        // 2. Product 정보 데이터베이스에 저장 & 반환
        return productRepository.save(Product.generateNewProduct(request));
    }

}
