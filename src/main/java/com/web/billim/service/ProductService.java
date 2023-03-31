package com.web.billim.service;

import com.web.billim.domain.ImageProduct;
import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.infra.ImageUploadService;
import com.web.billim.repository.ProductCategoryRepository;
import com.web.billim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final ImageUploadService imageUploadService;

    public Product register(ProductRegisterRequest request) {
        // 1. 이미지 저장
        List<ImageProduct> images = request.getImages().stream().map(image -> {
            try {
                String url = imageUploadService.upload(image, "/product");
                return ImageProduct.of(url);
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드중 에러가 발생했습니다.", e);
            }
        }).collect(Collectors.toList());

        // 2. Product 정보 데이터베이스에 저장 & 반환
        return productRepository.save(Product.generateNewProduct(request, images));
    }

    public List<ProductCategory> categoryList() {
        return productCategoryRepository.findAll();
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product retrieve(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        // Optional -> NULL 에 대한 처리를 좀 더 안전하게
        // NULL 이 저장된 객체를 쓰려고 에러가 난다. -> NPE
        // Optional -> 값이 들어있거나, 안들어있거나 두가지 상태를 가짐

        // N + 1 문제, Open-In-View 옵션, Proxy 에러
        Product product = productOptional.orElseThrow(() ->
                new RuntimeException("해당 ProductId(" + productId + ") 에 대한 상품정보가 없습니다."));
        return product;
    }
}
