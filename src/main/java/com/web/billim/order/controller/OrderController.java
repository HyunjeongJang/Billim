package com.web.billim.order.controller;

import com.web.billim.order.service.OrderService;
import com.web.billim.product.domain.Product;
import com.web.billim.product.dto.response.ProductDetailResponse;
import com.web.billim.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {


    @RequestMapping("/order/confirm")
    public String orderConfirm(
            String startDate,
            String endDate,
            int productId,
            Model model) {

        return "pages/order/orderInfo";
    }




}
