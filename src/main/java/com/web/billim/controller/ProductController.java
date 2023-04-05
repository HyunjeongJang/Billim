package com.web.billim.controller;

import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.dto.request.User;
import com.web.billim.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/list")
    public String productTotal(Model model,@RequestParam(required = false, defaultValue = "0", value = "page") int page
    ) {
        Page<Product> productList = productService.findAllProduct(page);
        model.addAttribute("productList", productList);
        model.addAttribute("totalPage", productList.getTotalPages());
        return "pages/product/productList";
    }

    @GetMapping("/product/detail/{productId}")
    public String productDetail(@PathVariable("productId") int productId, Model model) {
        Product product = productService.retrieve(productId);
        product.getImages().forEach(imageProduct -> {
            System.out.println(imageProduct.getUrl());
        });
        model.addAttribute("product", product);
        return "pages/product/productDetail";
    }


    @GetMapping("/myPage/purchase")
    public String myPage() {
        return "pages/myPage/myPurchaseList";
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
    public ResponseEntity<Product> registerProduct(@ModelAttribute @Valid ProductRegisterRequest req,
                                                   @AuthenticationPrincipal User user
    ) {
        req.setRegisterMember(user.getMember());
        return ResponseEntity.ok(productService.register(req));
    }






//    @GetMapping("/product/list")
//    public String productTotal(Model model) {
//        List<Product> productList = productService.findAllProduct();
//        model.addAttribute("productList", productList);
//        return "pages/product/productList";
//    }

//    @PostMapping(value = "/product/enroll", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseBody
//    public ResponseEntity<Product> registerProduct(@ModelAttribute @Valid ProductRegisterRequest productRegisterRequest,
//                                                   BindingResult bindingResult,
//                                                   @AuthenticationPrincipal User user,
//                                                   Model model
//    ) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("productRegisterRequest", productRegisterRequest);
//
//            Map<String, String> validatorResult = productService.validateHandling(bindingResult);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
////            return
//        }
//        productRegisterRequest.setRegisterMember(user.getMember());
//        return ResponseEntity.ok(productService.register(productRegisterRequest));
//    }

//    @PostMapping("/product/enroll")
//    public String registerProduct(@Valid ProductRegisterRequest productRegisterRequest) {
//        Member testMember = Member.builder().memberId(1).build();
//        productRegisterRequest.setRegisterMember(testMember);
//        productService.register(productRegisterRequest);
//        return "pages/myPage/mySalesList";
//    }


}

