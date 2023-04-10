package com.web.billim.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
