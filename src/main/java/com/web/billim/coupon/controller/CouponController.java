package com.web.billim.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponController {

    /*
     *  1. 사용 가능한 쿠폰 목록 보기
     */

    @GetMapping("coupon/list")
    public String myCouponList() {
        return "pages/myPage/myCouponList";
    }

}
