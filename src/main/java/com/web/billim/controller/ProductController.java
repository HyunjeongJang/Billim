package com.web.billim.controller;

import com.web.billim.domain.Product;
import com.web.billim.domain.ProductCategory;
import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.dto.response.MyProductSalesResponse;
import com.web.billim.dto.response.ProductDetailResponse;
import com.web.billim.dto.response.ReservationDateResponse;
import com.web.billim.security.domain.User;
import com.web.billim.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/list")
    public String productTotal(@RequestParam(required = false, defaultValue = "0", value = "page") int page,
                               Model model
    ) {
        Page<Product> productList = productService.findAllProduct(page);
        model.addAttribute("productList", productList);
        model.addAttribute("totalPage", productList.getTotalPages());
        return "pages/product/productList";
    }


    @GetMapping("/product/detail/{productId}")
    public String productDetail(@PathVariable("productId") int productId, Model model) {
        ProductDetailResponse productDetail = productService.retrieve(productId);

//        ReservationDateResponse dateList  = productService.reservationDate(productId);

        model.addAttribute("product", productDetail);
        return "pages/product/productDetail";
    }


//    @GetMapping("/product/detail/{productId}")
//    public String productDetail(@PathVariable("productId") int productId, Model model) {
//        Product product = productService.retrieve(productId);
//        product.getImages().forEach(imageProduct -> {
//            System.out.println(imageProduct.getUrl());
//        });
//        model.addAttribute("product", product);
//        return "pages/product/productDetail";
//    }

    @GetMapping("/myPage/purchase")
    public String myPage() {
        return "pages/myPage/myPurchaseList";
    }


//    @GetMapping("/myPage/sales")
//    public String myPageSalesManagement(Model model,@AuthenticationPrincipal User user) {
//
//        List<Product> products = productService.myProduceSales(user);
//        model.addAttribute("myProducts",products);
//        return "pages/myPage/mySalesList";
//    }


    @GetMapping("/myPage/sales")
    public String myPageSalesManagement(Model model, @AuthenticationPrincipal User user) {
        List<MyProductSalesResponse> products = productService.myProduceSales(user);
        model.addAttribute("myProducts", products);
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
        req.setRegisterMember(user.getMemberId());
        return ResponseEntity.ok(productService.register(req));
    }



}

