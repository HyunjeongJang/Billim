package com.web.billim.controller;

import com.web.billim.domain.Member;
import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/total")
    public String productTotal(Model model) {
        List<Product> productList = productService.findAllProduct();
        model.addAttribute("productList", productList);
        return "pages/product/productList";
    }

    @GetMapping("/product/detail/{productId}")
    public String productDetail(@PathVariable("productId") int productId, Model model) {


        return "pages/product/productDetail";
    }

    @GetMapping("/myPage/sales")
    public String myPageSalesManagement() {
        return "pages/myPage/mySalesList";
    }

    @GetMapping("/product/enrollForm")
    public String productEnroll(Model model) {
        List<ProductCategory> categoryList = productService.categoryList();
        model.addAttribute("categoryList", categoryList);
        return "pages/product/productEnroll";
    }

    @PostMapping("/product/enroll")
    @ResponseBody
    public ResponseEntity<Product> registerProduct(@Valid @RequestBody ProductRegisterRequest req) {
        Member testMember = Member.builder().memberId(1).build();
        req.setRegisterMember(testMember);
        return ResponseEntity.ok(productService.register(req));
    }

//    @PostMapping("/product/enroll")
//    public String registerProduct(@Valid ProductRegisterRequest req) {
//        Member testMember = Member.builder().memberId(1).build();
//        req.setRegisterMember(testMember);
//        productService.register(req);
//        return "pages/myPage/mySalesList";
//    }


}
