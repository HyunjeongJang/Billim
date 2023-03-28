package com.web.billim.service;

import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.repository.ProductCategoryRepository;
import com.web.billim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public Product register(ProductRegisterRequest request) {
        // 1. 이미지 저장
        // 2. Product 정보 데이터베이스에 저장 & 반환
        return productRepository.save(Product.generateNewProduct(request));
    }

    public List<ProductCategory> categoryList() {
        return productCategoryRepository.findAll();
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }





}
