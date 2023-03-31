package com.web.billim.controller;

import com.web.billim.domain.Member;
import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.service.MemberService;
import com.web.billim.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final MemberService memberService;

    @GetMapping("/product/total")
    public String productTotal(Model model) {
        List<Product> productList = productService.findAllProduct();
        model.addAttribute("productList", productList);
        return "pages/product/productList";
    }

    @GetMapping("/product/detail/{productId}")
    public String productDetail(@PathVariable("productId") int productId, Model model) {
        Product product = productService.retrieve(productId);
        model.addAttribute("product", product);
        return "pages/product/productDetail";
    }

    @GetMapping("/myPage/sales")
    public String myPageSalesManagement() {
        return "pages/myPage/mySalesList";
    }

    @GetMapping("/product/enroll")
    public String productEnroll(Model model) {
        List<ProductCategory> categoryList = productService.categoryList();
        model.addAttribute("categoryList", categoryList);
        return "pages/product/productEnroll";
    }

    @PostMapping(value = "/product/enroll", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Product> registerProduct(@ModelAttribute ProductRegisterRequest req) {
        Member testMember = Member.builder().memberId(1).build();
        req.setRegisterMember(testMember);
        return ResponseEntity.ok(productService.register(req));
    }

//    @PostMapping("/product/enroll")
//    public String registerProduct(@Valid ProductRegisterRequest productRegisterRequest) {
//        Member testMember = Member.builder().memberId(1).build();
//        productRegisterRequest.setRegisterMember(testMember);
//        productService.register(productRegisterRequest);
//        return "pages/myPage/mySalesList";
//    }
}

/**
 *  AWS S3 저장소에 Image 를 저장
 *   1. AWS S3 에 Image 를 업로드 -> S3 는 정적 파일(Image, JS, CSS, HTML ..)을 저장하는 웹하드
 *   2. AWS S3 에서 업로드된 파일에 접근할 수 있는 URL 을 제공해 줌
 *   3. DB 에 이 URL 만 저장해두고
 *   4. 그 URL 을 통해서 Client(사용자) 가 이미지에 접근할 수 있도록 한다.
 *
 *  AWS S3 세팅
 *   1. AWS IAM 계정
 *   2. 그 계정에 S3 Full Access 권한 부여
 *   3. S3 Bucket 만들고
 *   4. S3 Bucket 에 접근할 수 있는 권한, 정책
 *   5. Spring 으로 넘어올거....
 */
