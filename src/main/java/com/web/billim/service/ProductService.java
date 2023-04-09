package com.web.billim.service;

import java.io.IOException;
import java.time.LocalDate;
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
    private final OrderRepository orderRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ImageProductRepository imageProductRepository;
    private final ImageUploadService imageUploadService;


    public Product register(ProductRegisterRequest request) {
        Member registerMember = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));

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
        return productRepository.save(Product.generateNewProduct(request, registerMember, images));

    }

    public List<ProductCategory> categoryList() {
        return productCategoryRepository.findAll();
    }



    public Page<Product> findAllProduct(int page) {
        PageRequest paging = PageRequest.of(page, 12);
        return productRepository.findAll(paging);
    }


//    @Transactional
//    public Page<Product> searchByKeyword(String keyword, Pageable pageable) {
//        return productRepository.findByProductNameAndDetailContaining(keyword, pageable);
//    }





    @Transactional
    public ProductDetailResponse retrieve(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        // Optional -> NULL 에 대한 처리를 좀 더 안전하게
        // NULL 이 저장된 객체를 쓰려고 에러가 난다. -> NPE
        // Optional -> 값이 들어있거나, 안들어있거나 두가지 상태를 가짐

        // TODO : CustomException
        Product product = productOptional.orElseThrow(() ->
                new RuntimeException("해당 ProductId(" + productId + ") 에 대한 상품정보가 없습니다."));
        return ProductDetailResponse.of(product);
    }


//    public List<Product> myProduceSales(User user) {
//         List<Product> products= productRepository.findByMember_memberId(user.getMember().getMemberId());
//         return products;
//    }


    public List<MyProductSalesResponse> myProduceSales(User user) {
//         List<Product> products = productRepository.findByMember_memberId(user.getMemberId());
//         List<MyProductSalesResponse> myProductSalesResponses = products.stream().map(product -> {
//         	return MyProductSalesResponse.of(product);
//         }).collect(Collectors.toList());

        return productRepository.findByMember_memberId(user.getMemberId()).stream()
                .map(MyProductSalesResponse::of)
                .collect(Collectors.toList());
    }


    public ReservationDateResponse reservationDate(int productId) {
        Optional<Order> order =  orderRepository.findByProductId(productId)
                .orElseThrow(()->
                        new RuntimeException("해당 ProductId(" + productId + ") 에 대한 상품정보가 없습니다.");
        return order.stream().map(ReservationDateResponse::of)
                .collect(Collectors.toList());

    }
}

