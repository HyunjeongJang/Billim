package com.web.billim.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    @GetMapping ("/order/confirm")
    public String orderConfirm(String startDate, String endDate, int productId, Model model) {

        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate", endDate);

        return "pages/order/orderInfo";
    }


}
