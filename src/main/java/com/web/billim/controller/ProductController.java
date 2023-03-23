package com.web.billim.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/product/total")
    public String productTotal(){
        return "pages/product/product_total";
    }
}
