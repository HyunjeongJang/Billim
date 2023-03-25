package com.web.billim.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/product/total")
    public String productTotal(){
        return "pages/product/total_content";
    }

    @GetMapping("/myPage/sales")
    public String myPageSalesManagement(){
        return "pages/product/myPage_sales_content";
    }

    @GetMapping("/product/enroll")
    public String productEnroll(){
        return "pages/product/enroll_content";
    }

}
