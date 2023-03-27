package com.web.billim.controller;

import com.web.billim.domain.Member;
import com.web.billim.domain.Product;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/total")
    public String productTotal(){
        return "pages/product/productList";
    }

    @GetMapping("/myPage/sales")
    public String myPageSalesManagement(){
        return "pages/myPage/mySalesList";
    }

    @GetMapping("/product/enroll")
    public String productEnroll(){
        return "pages/product/productEnroll";
    }

    @PostMapping( "/product")
    @ResponseBody
    public ResponseEntity<Product> registerProduct(@Valid @RequestBody ProductRegisterRequest req) {
        Member testMember = Member.builder().memberId(1).build();

        req.setRegisterMember(testMember);
        return ResponseEntity.ok(productService.register(req));
    }

}
