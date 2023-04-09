package com.web.billim.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.billim.domain.*;
import com.web.billim.dto.response.MyProductSalesResponse;
import com.web.billim.dto.response.ProductDetailResponse;
import com.web.billim.dto.response.ReservationDateResponse;
import com.web.billim.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.security.domain.User;
import com.web.billim.infra.ImageUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ImageProductRepository imageProductRepository;
    private final OrderRepository orderRepository;
    private final ImageUploadService imageUploadService;


    public Product register(ProductRegisterRequest request) {
        Member registerMember = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
        ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."));

        // 1. 이미지 저장
        List<ImageProduct> images = request.getImages().stream().map(image -> {
            try {
                String url = imageUploadService.upload(image, "product");
                return imageProductRepository.save(ImageProduct.of(url));
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드중 에러가 발생했습니다.", e);
            }
        }).collect(Collectors.toList());

        // 2. Product 정보 데이터베이스에 저장 & 반환
        Product product = Product.generateNewProduct(request, productCategory, registerMember, images);
        return productRepository.save(product);
    }

    public List<ProductCategory> categoryList() {
        return productCategoryRepository.findAll();
    }

    public Page<Product> findAllProduct(int page) {
        PageRequest paging = PageRequest.of(page, 12);
        return productRepository.findAll(paging);
    }

    @Transactional
    public ProductDetailResponse retrieve(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        // TODO : CustomException
        Product product = productOptional.orElseThrow(() ->
                new RuntimeException("해당 ProductId(" + productId + ") 에 대한 상품정보가 없습니다."));
        return ProductDetailResponse.of(product);
    }


    public List<MyProductSalesResponse> myProduceSales(User user) {

        return productRepository.findByMember_memberId(user.getMemberId()).stream()
                .map(MyProductSalesResponse::of)
                .collect(Collectors.toList());
    }


//    public ReservationDateResponse reservationDate(int productId) {
//        Optional<Order> order = Optional.ofNullable(orderRepository.findByProductId(productId)
//                .orElseThrow(() ->
//                        new RuntimeException("해당 ProductId(" + productId + ") 에 대한 예약날짜가 없습니다.")));
//        return (ReservationDateResponse) order.stream().map(ReservationDateResponse::of)
//                .collect(Collectors.toList());
//
//    }





}

